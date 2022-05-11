package uma.taw.ubay.service;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import lombok.NonNull;
import org.springframework.security.crypto.bcrypt.BCrypt;
import uma.taw.ubay.AuthKeys;
import uma.taw.ubay.dao.ClientFacade;
import uma.taw.ubay.dao.LoginCredentialsFacade;
import uma.taw.ubay.dao.PasswordResetFacade;
import uma.taw.ubay.dto.LoginDTO;
import uma.taw.ubay.entity.*;
import uma.taw.ubay.exception.AuthenticationException;

/**
 * @author Altair Bueno
 */
@Stateless
public class AuthService {
    @EJB
    LoginCredentialsFacade loginCredentialsFacade;
    @EJB
    ClientFacade clientFacade;
    @EJB
    PasswordResetFacade passwordResetFacade;

    private String getHashedPassword(@NonNull String newPassword) {
        return BCrypt.hashpw(newPassword, BCrypt.gensalt(11));
    }

    private boolean checkPasswordHash(@NonNull String oldPassword, @NonNull String oldHash) {
        return BCrypt.checkpw(oldPassword, oldHash);
    }

    public void changePassword(@NonNull LoginDTO loginDTO, @NonNull String oldPassword, @NonNull String newPassword, @NonNull String repeatPassword) throws AuthenticationException {
        if (!repeatPassword.equals(newPassword))
            throw new AuthenticationException("Passwords don't match");
        if (!newPassword.matches(AuthKeys.PASSWORD_REGEX))
            throw new AuthenticationException("Invalid password format");

        LoginCredentialsEntity loginCredentials = getCredentialsEntity(loginDTO);
        String oldHash = loginCredentials.getPassword();

        if (checkPasswordHash(oldPassword, oldHash)) {
            String newHash = getHashedPassword(newPassword);
            loginCredentials.setPassword(newHash);
            loginCredentialsFacade.edit(loginCredentials);
        } else {
            throw new AuthenticationException("Old password doesn't match");
        }
    }

    public LoginDTO login(@NonNull String username, @NonNull String password) throws AuthenticationException {
        if (!username.matches(AuthKeys.USERNAME_REGEX))
            throw new AuthenticationException("Invalid username format");
        if (!password.matches(AuthKeys.PASSWORD_REGEX))
            throw new AuthenticationException("Invalid password format");

        LoginCredentialsEntity entity = loginCredentialsFacade.find(username);

        if (entity != null && checkPasswordHash(password, entity.getPassword())) {
            username = entity.getUsername();
            var kind = entity.getKind();
            return new LoginDTO(username,kind);
        } else {
            throw new AuthenticationException("Invalid username or password");
        }
    }

    public void register(
            @NonNull String username,
            @NonNull String password,
            @NonNull String repeatPassword,
            @NonNull String name,
            @NonNull String lastName,
            @NonNull String address,
            @NonNull String city,
            @NonNull String gender,
            @NonNull String birthDate
    ) throws AuthenticationException {
        if (!username.matches(AuthKeys.USERNAME_REGEX))
            throw new AuthenticationException("Username invalid format");
        if (!password.matches(AuthKeys.PASSWORD_REGEX))
            throw new AuthenticationException("Password invalid format");
        if (!password.equals(repeatPassword))
            throw new AuthenticationException("Passwords don't match");

        java.sql.Date parsedBirthDate = java.sql.Date.valueOf(birthDate);
        GenderEnum parsedGender = GenderEnum.valueOf(gender);
        String hashedPassword = getHashedPassword(password);

        ClientEntity client = new ClientEntity(name, lastName, address, city, parsedBirthDate, parsedGender);
        LoginCredentialsEntity login = new LoginCredentialsEntity(username, hashedPassword, KindEnum.client, client);

        clientFacade.create(client);
        loginCredentialsFacade.create(login);
    }

    public void resetPassword(@NonNull String username, @NonNull String requestID, @NonNull String newPassword, @NonNull String repeatPassword) throws AuthenticationException {
        if (!newPassword.equals(repeatPassword))
            throw new AuthenticationException("Passwords don't match");
        if (!newPassword.matches(AuthKeys.PASSWORD_REGEX))
            throw new AuthenticationException("Invalid password format");

        PasswordResetEntityPK key = new PasswordResetEntityPK(username, requestID);
        PasswordResetEntity passwordResetEntity = passwordResetFacade.find(key);

        LoginCredentialsEntity credentialsEntity = passwordResetEntity.getUser();
        String hashedPassword = getHashedPassword(newPassword);
        credentialsEntity.setPassword(hashedPassword);
        passwordResetFacade.remove(passwordResetEntity);
        loginCredentialsFacade.edit(credentialsEntity);
    }

    public LoginCredentialsEntity getCredentialsEntity(@NonNull LoginDTO loginDTO) {
        return loginCredentialsFacade.find(loginDTO.getUsername());
    }

}
