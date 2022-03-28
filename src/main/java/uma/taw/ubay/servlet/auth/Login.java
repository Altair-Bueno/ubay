package uma.taw.ubay.servlet.auth;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import uma.taw.ubay.RequestKeys;
import uma.taw.ubay.SessionKeys;
import uma.taw.ubay.auth.Auth;
import uma.taw.ubay.dao.LoginCredentialsFacade;
import uma.taw.ubay.entity.LoginCredentialsEntity;

import java.io.IOException;

@WebServlet("/auth/login")
public class Login extends HttpServlet {
    @EJB
    LoginCredentialsFacade facade;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter(Auth.USERNAME_PARAMETER);
        String password = req.getParameter(Auth.PASSWORD_PARAMETER);

        if (
                username == null
                || password == null
                || !password.matches(Auth.PASSWORD_REGEX)
                || !username.matches(Auth.USERNAME_REGEX)
        ) {
            resp.setStatus(400);
            return;
        }

        LoginCredentialsEntity entity = facade.find(username);
        boolean matches = entity != null &&
                entity.getPassword().equals(password);

        if (matches) {
            HttpSession session = req.getSession(); // fixme: Safari rejects this setting
            session.setAttribute(SessionKeys.LOGIN_CREDENTIALS,entity);
            resp.sendRedirect(Auth.INDEX_REDIRECT);
        } else {
            req.setAttribute(RequestKeys.ERROR,Auth.ERROR_MESSAGE);
            req.getRequestDispatcher(Auth.LOGIN_REDIRECT).forward(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(Auth.LOGIN_REDIRECT).forward(req,resp);
    }
}
