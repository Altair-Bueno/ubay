package uma.taw.ubay.servlet.admin;

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
import java.util.List;

@WebServlet("/admin/users")
public class Users extends HttpServlet {
    @EJB
    ClientFacade facade;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {process(request, response);}

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {process(request,response);}

    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String search = request.getParameter("search");
        String filter = request.getParameter("filterBy");
        String deleteOn = request.getParameter("delete");
        String userID = request.getParameter("userID");
        List<ClientEntity> clientEntityList = facade.findAll();
        request.setAttribute("users-list",clientEntityList);
        try{
            if(!filter.equals("--") && search != null){
                switch(filter){
                    case "Name" : request.setAttribute("search-user", facade.filterByName(search)); break;
                    case "Address" : request.setAttribute("search-user", facade.filterByAddress(search)); break;
                    case "Gender" : request.setAttribute("search-user", facade.filterByGender(GenderEnum.valueOf(search))); break;
                    case "City" : request.setAttribute("search-user", facade.filterByCity(search)); break;
                    case "ID" : request.setAttribute("search-user", facade.filterByID(search)); break;
                }
            } else {
                request.setAttribute("search-user", clientEntityList);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        request.getRequestDispatcher("users.jsp").forward(request,response);
    }
}
