package uma.taw.ubay.servlet.admin;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uma.taw.ubay.dao.CategoryFacade;
import uma.taw.ubay.entity.CategoryEntity;
import java.io.IOException;

@WebServlet("/admin/addCategory")
public class AddCategory extends HttpServlet {
    @EJB
    CategoryFacade facade;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {process(request, response);}

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {process(request,response);}

    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        if(name != null && description != null){
            CategoryEntity category = new CategoryEntity();
            category.setName(name);
            category.setDescription(description);
            facade.create(category);
        }

        request.getRequestDispatcher("addCategory.jsp").forward(request,response);
    }
}

