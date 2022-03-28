package uma.taw.ubay.servlet.auth;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import uma.taw.ubay.dao.LoginCredentialsFacade;
import uma.taw.ubay.entity.LoginCredentialsEntity;

import java.io.IOException;

@WebServlet("/auth/login")
public class Login extends HttpServlet {

    private final static String PASSWORD_INVALID_REGEX = "[ ]";

    @EJB
    LoginCredentialsFacade facade;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username == null || password == null || password.matches(PASSWORD_INVALID_REGEX)) {
            resp.setStatus(400);
            return;
        }

        LoginCredentialsEntity entity = facade.find(username);
        boolean matches = entity != null &&
                entity.getPassword().equals(password);

        if (matches) {
            HttpSession session = req.getSession(); // Note: Safari rejects this setting
            session.setAttribute("login",entity);
            resp.sendRedirect("../");
        } else {
            req.setAttribute("cause","Bad username or password");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req,resp);
    }
}
