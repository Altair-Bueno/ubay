package uma.taw.ubay.servlet.auth;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.crypto.bcrypt.BCrypt;
import uma.taw.ubay.AuthKeys;
import uma.taw.ubay.SessionKeys;
import uma.taw.ubay.dao.LoginCredentialsFacade;
import uma.taw.ubay.entity.LoginCredentialsEntity;

import java.io.IOException;

@WebServlet("/auth/changePassword")
public class ChangePassword extends HttpServlet{
    @EJB
    LoginCredentialsFacade facade;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter(AuthKeys.OLD_PASSWORD_PARAMETER);
        String newPassword = req.getParameter(AuthKeys.PASSWORD_PARAMETER);
        String repeatPassword = req.getParameter(AuthKeys.REPEAT_PASSWORD_PARAMETER);

        if (repeatPassword.equals(newPassword)) {
            LoginCredentialsEntity loginCredentials = (LoginCredentialsEntity) req.getSession().getAttribute(SessionKeys.LOGIN_CREDENTIALS);
            loginCredentials = facade.find(loginCredentials.getUsername());

            String originalHash = loginCredentials.getPassword();

            if (BCrypt.checkpw(password,originalHash)) {
                String newHash = BCrypt.hashpw(newPassword,BCrypt.gensalt(11));
                loginCredentials.setPassword(newHash);
                facade.edit(loginCredentials);
            } else {
                // Unauthorised
                resp.sendError(401,"Old password doesn't match");
            }
        } else {
            resp.sendError(400,"The passwords don't match");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("changePassword.jsp").forward(req,resp);
    }
}
