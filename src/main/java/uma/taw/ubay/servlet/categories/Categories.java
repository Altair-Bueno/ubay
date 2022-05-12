package uma.taw.ubay.servlet.categories;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uma.taw.ubay.SessionKeys;
import uma.taw.ubay.dto.LoginDTO;
import uma.taw.ubay.dto.categories.CategoriesDTO;
import uma.taw.ubay.service.categories.CategoriesService;

import java.io.IOException;

/**
 * @author José Luis Bueno Pachón
 */

@WebServlet("/categories/")
public class Categories extends HttpServlet {
    @EJB
    CategoriesService categoriesService;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {process(request, response);}

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {process(request,response);}

    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        var loginDTO = (LoginDTO) request.getSession().getAttribute(SessionKeys.LOGIN_DTO);

        CategoriesDTO categoriesDTO = categoriesService.processCategories(loginDTO);

        request.setAttribute("user-fav-category-list", categoriesDTO.getUserFavouriteCategories());
        request.setAttribute("category-list",  categoriesDTO.getCategoryList());
        request.setAttribute("client-id", categoriesDTO.getUserID());

        request.getRequestDispatcher("categories.jsp").forward(request,response);
    }



}
