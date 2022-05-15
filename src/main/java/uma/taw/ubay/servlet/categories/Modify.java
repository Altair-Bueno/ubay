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

@WebServlet("/categories/modify")
public class Modify extends HttpServlet {
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
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String edited = request.getParameter("edited");

        if (edited == null) {
            request.getRequestDispatcher("modify.jsp").forward(request, response);
        } else {
            categoriesService.modify(id, name, description);
            response.sendRedirect(request.getContextPath() + "/categories/");
        }
    }

}
