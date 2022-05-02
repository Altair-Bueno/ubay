package uma.taw.ubay.servlet.categories;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uma.taw.ubay.UbayException;
import uma.taw.ubay.dao.CategoryFacade;
import uma.taw.ubay.dao.ClientFacade;
import uma.taw.ubay.dao.UserFavouritesFacade;
import uma.taw.ubay.entity.CategoryEntity;
import uma.taw.ubay.entity.ClientEntity;
import uma.taw.ubay.entity.UserFavouritesEntity;
import uma.taw.ubay.service.categories.CategoriesService;

import java.io.IOException;

@WebServlet("/categories/deleteFavourite")
public class DeleteFavourite extends HttpServlet {
    @EJB
    CategoriesService categoriesService;


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {process(request, response);}

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {process(request,response);}

    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String clientID = request.getParameter("clientID");
        String categoryID = request.getParameter("categoryID");

        categoriesService.deleteFavourite(clientID, categoryID);

        request.getRequestDispatcher("deleteFavourite.jsp").forward(request,response);
    }


}
