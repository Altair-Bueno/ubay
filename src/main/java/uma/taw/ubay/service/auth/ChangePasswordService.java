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
public class ChangePasswordService {
    @EJB
    LoginCredentialsFacade facade;

    public void changePassword(@NonNull LoginDTO loginDTO, @NonNull String oldPassword, @NonNull String newPassword, @NonNull String repeatPassword) {
        if (!repeatPassword.equals(newPassword))
            throw new RuntimeException("The passwords don't match");
        if (!newPassword.matches(AuthKeys.PASSWORD_REGEX))
            throw new RuntimeException("Password doesn't match the password regex");

        LoginCredentialsEntity loginCredentials = facade.find(loginDTO.getUsername());
        String oldHash = loginCredentials.getPassword();

        if (BCrypt.checkpw(oldPassword, oldHash)) {
            String newHash = BCrypt.hashpw(newPassword,BCrypt.gensalt(11));
            loginCredentials.setPassword(newHash);
            facade.edit(loginCredentials);
        } else {
            throw new RuntimeException("Old password doesn't match");
        }
    }
}
