package uma.taw.ubay.servlet.auth;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uma.taw.ubay.AuthKeys;
import uma.taw.ubay.service.auth.RegisterService;

import java.io.IOException;

/**
 * Servlet in charge of creating a new user session with the received username
 * and password
 */
@WebServlet("/auth/register")
public class Register extends HttpServlet {
    @EJB
    RegisterService service;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String usernameParameter = req.getParameter(AuthKeys.USERNAME_PARAMETER);
        String passwordParameter = req.getParameter(AuthKeys.PASSWORD_PARAMETER);
        String repeatPasswordParameter = req.getParameter(AuthKeys.REPEAT_PASSWORD_PARAMETER);

        String nameParameter = req.getParameter(AuthKeys.NAME_PARAMETER);
        String lastNameParameter = req.getParameter(AuthKeys.LAST_NAME_PARAMETER);
        String addressParameter = req.getParameter(AuthKeys.ADDRESS_PARAMETER);
        String cityParameter = req.getParameter(AuthKeys.CITY_PARAMETER);
        String genderParameter = req.getParameter(AuthKeys.GENDER_PARAMETER);
        String birthDateParameter = req.getParameter(AuthKeys.BIRTH_PARAMETER);

        service.register(usernameParameter,
                passwordParameter,
                repeatPasswordParameter,
                nameParameter,
                lastNameParameter,
                addressParameter,
                cityParameter,
                genderParameter,
                birthDateParameter);

        resp.sendRedirect(req.getContextPath() + AuthKeys.LOGIN_REDIRECT);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }
}
