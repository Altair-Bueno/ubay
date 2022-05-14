package uma.taw.ubay.servlet.users;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uma.taw.ubay.dto.users.ClientDTO;
import uma.taw.ubay.service.users.UsersService;

import java.io.IOException;
import java.util.List;

/**
 * @author José Luis Bueno Pachón
 */

@WebServlet("/users/")
public class Users extends HttpServlet {
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
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String gender = request.getParameter("gender");

        List<ClientDTO> clientDTOList = usersService.users(id, name, lastName, address, city, gender);

        request.setAttribute("search-user", clientDTOList);
        request.getRequestDispatcher("users.jsp").forward(request, response);
    }
}
