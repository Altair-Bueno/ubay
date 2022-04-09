package uma.taw.ubay.servlet.auth;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.crypto.bcrypt.BCrypt;
import uma.taw.ubay.AuthKeys;
import uma.taw.ubay.dao.LoginCredentialsFacade;
import uma.taw.ubay.dao.PasswordResetFacade;
import uma.taw.ubay.entity.LoginCredentialsEntity;
import uma.taw.ubay.entity.PasswordResetEntity;
import uma.taw.ubay.entity.PasswordResetEntityPK;

import java.io.IOException;
import java.util.Objects;
import java.util.stream.Stream;

@WebServlet("/auth/resetPassword")
public class ResetPassword extends HttpServlet {
    @EJB
    LoginCredentialsFacade loginCredentialsFacade;
    @EJB
    PasswordResetFacade passwordResetFacade;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter(AuthKeys.USERNAME_PARAMETER);
        String requestID = req.getParameter(AuthKeys.PASSWORD_CHANGE_ID_PARAMETER);
        String newPassword = req.getParameter(AuthKeys.PASSWORD_PARAMETER);
        String repeatPassword = req.getParameter(AuthKeys.REPEAT_PASSWORD_PARAMETER);

        if (Stream.of(requestID,newPassword,repeatPassword).anyMatch(Objects::isNull))
            throw new IllegalArgumentException("All fields are required");
        if (!newPassword.equals(repeatPassword))
            throw new IllegalArgumentException("Passwords doesn't match");
        if (!newPassword.matches(AuthKeys.PASSWORD_REGEX))
            throw new IllegalArgumentException("Invalid password format");

        PasswordResetEntity passwordResetEntity = passwordResetFacade.find(new PasswordResetEntityPK(username,requestID));
        if (passwordResetEntity == null)
            throw new RuntimeException("Password reset request doesn't exist");

        LoginCredentialsEntity credentialsEntity = passwordResetEntity.getUser();
        String hashedPassword = BCrypt.hashpw(newPassword,BCrypt.gensalt(11));
        credentialsEntity.setPassword(hashedPassword);
        loginCredentialsFacade.edit(credentialsEntity);
        passwordResetFacade.remove(passwordResetEntity);

        resp.sendRedirect(req.getContextPath());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/auth/resetPassword.jsp").forward(req,resp);
    }
}
