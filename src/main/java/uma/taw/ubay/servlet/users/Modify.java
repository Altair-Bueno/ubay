package uma.taw.ubay.servlet.users;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uma.taw.ubay.entity.GenderEnum;
import uma.taw.ubay.service.users.UsersService;

import java.io.IOException;
import java.sql.Date;

/**
 * @author José Luis Bueno Pachón
 */

@WebServlet("/users/modify")
public class Modify extends HttpServlet {
    @EJB
    UsersService usersService;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
    }

    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        GenderEnum gender = GenderEnum.valueOf(request.getParameter("gender"));
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        Date birthDate = java.sql.Date.valueOf(request.getParameter("birthDate"));
        String edited = request.getParameter("edited");
        if (edited == null) {
            request.getRequestDispatcher("modify.jsp").forward(request, response);
        } else {
            usersService.modifyUser(id, name, lastName, gender, address, city, birthDate);
            response.sendRedirect(request.getContextPath() + "/users/");
        }

    }

}
