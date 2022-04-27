package uma.taw.ubay.service.auth;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import lombok.NonNull;
import org.springframework.security.crypto.bcrypt.BCrypt;
import uma.taw.ubay.AuthKeys;
import uma.taw.ubay.dao.LoginCredentialsFacade;
import uma.taw.ubay.dto.auth.LoginDTO;
import uma.taw.ubay.entity.LoginCredentialsEntity;

@Stateless
public class LoginService {
    @EJB
    LoginCredentialsFacade facade;

    public LoginDTO login(@NonNull String username, @NonNull String password) {
        if (!username.matches(AuthKeys.USERNAME_REGEX))
            throw new RuntimeException("Invalid username format");
        if (!password.matches(AuthKeys.PASSWORD_REGEX))
            throw new RuntimeException("Invalid password format");

        LoginCredentialsEntity entity = facade.find(username);

        if (entity != null && BCrypt.checkpw(password,entity.getPassword())){
            return new LoginDTO(username);
        } else {
            throw new RuntimeException("Invalid username or password");
        }
    }
}
