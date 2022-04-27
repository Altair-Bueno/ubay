package uma.taw.ubay.service.auth;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import lombok.NonNull;
import org.springframework.security.crypto.bcrypt.BCrypt;
import uma.taw.ubay.AuthKeys;
import uma.taw.ubay.dao.LoginCredentialsFacade;
import uma.taw.ubay.dao.PasswordResetFacade;
import uma.taw.ubay.entity.LoginCredentialsEntity;
import uma.taw.ubay.entity.PasswordResetEntity;
import uma.taw.ubay.entity.PasswordResetEntityPK;

@Stateless
public class ResetPasswordService {
    @EJB
    LoginCredentialsFacade loginCredentialsFacade;
    @EJB
    PasswordResetFacade passwordResetFacade;
    public void resetPassword(@NonNull String username, @NonNull String requestID, @NonNull String newPassword, @NonNull String repeatPassword) {
        if (!newPassword.equals(repeatPassword))
            throw new IllegalArgumentException("Passwords don't match");
        if (!newPassword.matches(AuthKeys.PASSWORD_REGEX))
            throw new IllegalArgumentException("Invalid password format");

        PasswordResetEntityPK key = new PasswordResetEntityPK(username, requestID);
        PasswordResetEntity passwordResetEntity = passwordResetFacade.find(key);

        LoginCredentialsEntity credentialsEntity = passwordResetEntity.getUser();
        String hashedPassword = BCrypt.hashpw(newPassword,BCrypt.gensalt(11));
        credentialsEntity.setPassword(hashedPassword);
        passwordResetFacade.remove(passwordResetEntity);
        loginCredentialsFacade.edit(credentialsEntity);
    }
}
