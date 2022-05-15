package uma.taw.ubay.servlet.categories;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uma.taw.ubay.service.categories.CategoriesService;

import java.io.IOException;

/**
 * @author José Luis Bueno Pachón
 */

@WebServlet("/categories/addFavourite")
public class AddFavourite extends HttpServlet {
    @EJB
    CategoriesService categoriesService;


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
    }

    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String clientID = request.getParameter("clientID");
        String categoryID = request.getParameter("categoryID");

        categoriesService.addFavouriteCategory(clientID, categoryID);

        request.getRequestDispatcher("addFavourite.jsp").forward(request, response);
    }


}
