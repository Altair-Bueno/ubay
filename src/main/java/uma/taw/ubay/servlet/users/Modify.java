package uma.taw.ubay.servlet.users;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uma.taw.ubay.dao.ClientFacade;
import uma.taw.ubay.entity.ClientEntity;
import uma.taw.ubay.entity.GenderEnum;

import java.io.IOException;
import java.sql.Date;

@WebServlet("/users/modify")
public class Modify extends HttpServlet {
    @EJB
    ClientFacade facadeClient;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {process(request, response);}

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {process(request,response);}

    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        GenderEnum gender = GenderEnum.valueOf(request.getParameter("gender"));
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        Date birthDate = java.sql.Date.valueOf(request.getParameter("birthDate"));
        ClientEntity client = facadeClient.find(Integer.parseInt(id));
        client.setName(name);
        client.setLastName(lastName);
        client.setGender(gender);
        client.setAddress(address);
        client.setCity(city);
        client.setBirthDate(birthDate);

        facadeClient.edit(client);

        request.getRequestDispatcher("modify.jsp").forward(request,response);
    }
}
