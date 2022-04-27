package uma.taw.ubay.servlet.auth;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uma.taw.ubay.AuthKeys;
import uma.taw.ubay.service.auth.ResetPasswordService;

import java.io.IOException;

@WebServlet("/auth/resetPassword")
public class ResetPassword extends HttpServlet {
    @EJB
    ResetPasswordService service;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usernameParameter = req.getParameter(AuthKeys.USERNAME_PARAMETER);
        String requestIDParameter = req.getParameter(AuthKeys.PASSWORD_CHANGE_ID_PARAMETER);
        String newPasswordParameter = req.getParameter(AuthKeys.PASSWORD_PARAMETER);
        String repeatPasswordParameter = req.getParameter(AuthKeys.REPEAT_PASSWORD_PARAMETER);

        service.resetPassword(usernameParameter, requestIDParameter, newPasswordParameter, repeatPasswordParameter);

        resp.sendRedirect(req.getContextPath());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/auth/resetPassword.jsp").forward(req,resp);
    }
}
