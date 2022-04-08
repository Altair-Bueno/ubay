package uma.taw.ubay.servlet.categories;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uma.taw.ubay.dao.CategoryFacade;
import uma.taw.ubay.entity.CategoryEntity;

import java.io.IOException;
import java.util.List;

@WebServlet("/categories/")
public class Categories extends HttpServlet {
    @EJB
    CategoryFacade facade;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {process(request, response);}

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {process(request,response);}

    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<CategoryEntity> catList = facade.findAll();
        request.setAttribute("category-list", catList);
        request.getRequestDispatcher("categories.jsp").forward(request,response);
    }
}
