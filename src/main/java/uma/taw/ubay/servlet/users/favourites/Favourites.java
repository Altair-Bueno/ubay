package uma.taw.ubay.servlet.users.favourites;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uma.taw.ubay.dao.ClientFacade;
import uma.taw.ubay.dao.UserFavouritesFacade;
import uma.taw.ubay.entity.ClientEntity;

import java.io.IOException;

@WebServlet("/users/favourites")
public class Favourites extends HttpServlet {
    @EJB
    ClientFacade clientFacade;

    @EJB
    UserFavouritesFacade userFavouritesFacade;



    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {process(request, response);}

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {process(request,response);}

    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        ClientEntity client = clientFacade.find(Integer.parseInt(id));



        request.getRequestDispatcher("favourites.jsp").forward(request,response);
    }
}

