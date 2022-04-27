package uma.taw.ubay.servlet.auth;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uma.taw.ubay.AuthKeys;
import uma.taw.ubay.SessionKeys;
import uma.taw.ubay.dto.auth.LoginDTO;
import uma.taw.ubay.service.auth.ChangePasswordService;

import java.io.IOException;

@WebServlet("/auth/changePassword")
public class ChangePassword extends HttpServlet{
    @EJB
    ChangePasswordService service;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oldPasswordParameter = req.getParameter(AuthKeys.OLD_PASSWORD_PARAMETER);
        String newPasswordParameter = req.getParameter(AuthKeys.PASSWORD_PARAMETER);
        String repeatPasswordParameter = req.getParameter(AuthKeys.REPEAT_PASSWORD_PARAMETER);

        LoginDTO loginDTO = (LoginDTO) req.getSession().getAttribute(SessionKeys.LOGIN_DTO);
        service.changePassword(loginDTO, oldPasswordParameter, newPasswordParameter, repeatPasswordParameter);

        resp.sendRedirect(req.getContextPath());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("changePassword.jsp").forward(req,resp);
    }
}
