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
        List<ClientEntity> clientEntityList = facade.findAll();
        request.setAttribute("users-list",clientEntityList);
        if(search != null){
            request.setAttribute("search-user", facade.filterByName(search));
        }

        request.getRequestDispatcher("users.jsp")
                .forward(request,response);
    }
}
