package uma.taw.ubay.servlet.auth;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCrypt;
import uma.taw.ubay.AuthKeys;
import uma.taw.ubay.SessionKeys;
import uma.taw.ubay.dao.LoginCredentialsFacade;
import uma.taw.ubay.entity.LoginCredentialsEntity;

import java.io.IOException;

/**
 * Servlet in charge of creating a new user session with the received username
 * and password. If the login fails, the client will receive a 401
 * (Unauthorised) response
 */

@WebServlet("/auth/login")
public class Login extends HttpServlet {
    @EJB
    LoginCredentialsFacade facade;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String referer = req.getHeader("Referer");
        String username = req.getParameter(AuthKeys.USERNAME_PARAMETER);
        String password = req.getParameter(AuthKeys.PASSWORD_PARAMETER);

        if (
                username == null
                || password == null
                || !password.matches(AuthKeys.PASSWORD_REGEX)
                || !username.matches(AuthKeys.USERNAME_REGEX)
        ) {
            resp.setStatus(400);
            return;
        }

        LoginCredentialsEntity entity = facade.find(username);
        boolean matches = entity != null &&
                BCrypt.checkpw(password,entity.getPassword());

        if (matches) {
            HttpSession session = req.getSession(); // fixme: Safari rejects this setting
            session.setAttribute(SessionKeys.LOGIN_CREDENTIALS,entity);
            resp.sendRedirect(referer == null ? req.getContextPath() : referer);
        } else {
            // 401 - Unauthorised
            resp.sendError(401,"Bad username or password");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req,resp);
    }
}
