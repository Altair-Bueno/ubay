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

    public void changePassword(@NonNull LoginDTO loginDTO, @NonNull String oldPassword, @NonNull String newPassword, @NonNull String repeatPassword) {
        if (!repeatPassword.equals(newPassword))
            throw new RuntimeException("The passwords don't match");
        if (!newPassword.matches(AuthKeys.PASSWORD_REGEX))
            throw new RuntimeException("Password doesn't match the password regex");

        LoginCredentialsEntity loginCredentials = loginCredentialsFacade.find(loginDTO.getUsername());
        String oldHash = loginCredentials.getPassword();

        if (checkPasswordHash(oldPassword, oldHash)) {
            String newHash = getHashedPassword(newPassword);
            loginCredentials.setPassword(newHash);
            loginCredentialsFacade.edit(loginCredentials);
        } else {
            throw new RuntimeException("Old password doesn't match");
        }
    }

    public LoginDTO login(@NonNull String username, @NonNull String password) {
        if (!username.matches(AuthKeys.USERNAME_REGEX))
            throw new RuntimeException("Invalid username format");
        if (!password.matches(AuthKeys.PASSWORD_REGEX))
            throw new RuntimeException("Invalid password format");

        LoginCredentialsEntity entity = loginCredentialsFacade.find(username);

        if (entity != null && checkPasswordHash(password, entity.getPassword())) {
            return new LoginDTO(username);
        } else {
            throw new RuntimeException("Invalid username or password");
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
    ) {
        if (!username.matches(AuthKeys.USERNAME_REGEX))
            throw new IllegalArgumentException("Username invalid format");
        if (!password.matches(AuthKeys.PASSWORD_REGEX))
            throw new IllegalArgumentException("Password invalid format");
        if (!password.equals(repeatPassword))
            throw new IllegalArgumentException("Passwords don't match");

        java.sql.Date parsedBirthDate = java.sql.Date.valueOf(birthDate);
        GenderEnum parsedGender = GenderEnum.valueOf(gender);
        String hashedPassword = getHashedPassword(password);

        ClientEntity client = new ClientEntity(name, lastName, address, city, parsedBirthDate, parsedGender);
        LoginCredentialsEntity login = new LoginCredentialsEntity(username, hashedPassword, KindEnum.client, client);

        clientFacade.create(client);
        loginCredentialsFacade.create(login);
    }

    public void resetPassword(@NonNull String username, @NonNull String requestID, @NonNull String newPassword, @NonNull String repeatPassword) {
        if (!newPassword.equals(repeatPassword))
            throw new IllegalArgumentException("Passwords don't match");
        if (!newPassword.matches(AuthKeys.PASSWORD_REGEX))
            throw new IllegalArgumentException("Invalid password format");

        PasswordResetEntityPK key = new PasswordResetEntityPK(username, requestID);
        PasswordResetEntity passwordResetEntity = passwordResetFacade.find(key);

        LoginCredentialsEntity credentialsEntity = passwordResetEntity.getUser();
        String hashedPassword = getHashedPassword(newPassword);
        credentialsEntity.setPassword(hashedPassword);
        passwordResetFacade.remove(passwordResetEntity);
        loginCredentialsFacade.edit(credentialsEntity);
    }

}
