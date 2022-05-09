package uma.taw.ubay.servlet.users;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uma.taw.ubay.SessionKeys;
import uma.taw.ubay.dao.ProductFacade;
import uma.taw.ubay.dto.LoginDTO;
import uma.taw.ubay.dto.notifications.BidsDTO;
import uma.taw.ubay.dto.users.ProductDTO;
import uma.taw.ubay.entity.LoginCredentialsEntity;
import uma.taw.ubay.entity.ProductEntity;
import uma.taw.ubay.service.users.UsersService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet("/users/notifications")
public class Notifications extends HttpServlet {
    @EJB
    UsersService usersService;

    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var login = (LoginDTO) req.getSession().getAttribute(SessionKeys.LOGIN_DTO);

        HashMap<BidsDTO, Boolean> notifications = usersService.getNotifications(login);


        req.setAttribute("notifications", notifications);
        req.getRequestDispatcher("notifications.jsp").forward(req, resp);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }
}
