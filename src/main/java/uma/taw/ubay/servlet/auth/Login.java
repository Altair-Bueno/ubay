package uma.taw.ubay.servlet.auth;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uma.taw.ubay.AuthKeys;
import uma.taw.ubay.SessionKeys;
import uma.taw.ubay.dto.LoginDTO;
import uma.taw.ubay.service.AuthService;

import java.io.IOException;

/**
 * Servlet in charge of creating a new user session with the received username
 * and password. If the login fails, the client will receive a 401
 * (Unauthorised) response
 *
 * @author Altair Bueno
 */

@WebServlet("/auth/login")
public class Login extends HttpServlet {
    @EJB
    AuthService service;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String usernameParameter = req.getParameter(AuthKeys.USERNAME_PARAMETER);
        String passwordParameter = req.getParameter(AuthKeys.PASSWORD_PARAMETER);

        LoginDTO username = service.login(usernameParameter, passwordParameter);
        req.getSession().setAttribute(SessionKeys.LOGIN_DTO, username);

        resp.sendRedirect(req.getContextPath() + AuthKeys.INDEX_REDIRECT);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req,resp);
    }
}
