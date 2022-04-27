package uma.taw.ubay.service.auth;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import lombok.NonNull;
import org.springframework.security.crypto.bcrypt.BCrypt;
import uma.taw.ubay.AuthKeys;
import uma.taw.ubay.dao.ClientFacade;
import uma.taw.ubay.dao.LoginCredentialsFacade;
import uma.taw.ubay.entity.ClientEntity;
import uma.taw.ubay.entity.GenderEnum;
import uma.taw.ubay.entity.KindEnum;
import uma.taw.ubay.entity.LoginCredentialsEntity;

@Stateless
public class RegisterService {
    @EJB
    LoginCredentialsFacade loginCredentialsFacade;
    @EJB
    ClientFacade clientFacade;
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
        String hashedPassword = BCrypt.hashpw(password,BCrypt.gensalt(11));

        ClientEntity client = new ClientEntity(name, lastName, address, city, parsedBirthDate, parsedGender);
        LoginCredentialsEntity login = new LoginCredentialsEntity(username, hashedPassword, KindEnum.client, client);

        clientFacade.create(client);
        loginCredentialsFacade.create(login);
    }
}
