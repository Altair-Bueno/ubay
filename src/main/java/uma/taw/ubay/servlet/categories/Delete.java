package uma.taw.ubay.servlet.categories;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uma.taw.ubay.UbayException;
import uma.taw.ubay.dao.CategoryFacade;
import uma.taw.ubay.entity.CategoryEntity;
import uma.taw.ubay.service.categories.CategoriesService;

import java.io.IOException;

@WebServlet("/categories/delete")
public class Delete extends HttpServlet {
    @EJB
    CategoriesService categoriesService;


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {process(request, response);}

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {process(request,response);}

    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");

        categoriesService.deleteCategory(id);

        request.getRequestDispatcher("delete.jsp").forward(request,response);
    }


}
