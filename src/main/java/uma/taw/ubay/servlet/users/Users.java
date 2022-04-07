package uma.taw.ubay.servlet.users;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uma.taw.ubay.dao.ClientFacade;
import uma.taw.ubay.dao.LoginCredentialsFacade;
import uma.taw.ubay.entity.ClientEntity;
import uma.taw.ubay.entity.GenderEnum;

import java.io.IOException;
import java.sql.Date;
import java.util.*;

@WebServlet("/users/")
public class Users extends HttpServlet {
    @EJB
    ClientFacade facade;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {process(request, response);}

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {process(request,response);}

    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String genderString = request.getParameter("gender");
        GenderEnum gender = null;
        if(genderString != null && !"".equals(genderString) && !genderString.equals("--")){
            gender = GenderEnum.valueOf(genderString);
        }

        List<ClientEntity> clientEntityList = new ArrayList<>();
        clientEntityList = facade.filterClients(name,lastName,gender,address,city,id);

        request.setAttribute("search-user", clientEntityList);
        request.getRequestDispatcher("users.jsp").forward(request,response);
    }
}
