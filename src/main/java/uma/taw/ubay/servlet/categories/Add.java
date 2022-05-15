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

@WebServlet("/categories/add")
public class Add extends HttpServlet {
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
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String added = request.getParameter("added");

        if (added == null) {
            request.getRequestDispatcher("add.jsp").forward(request, response);
        } else {
            categoriesService.addCategory(name, description);
            response.sendRedirect(request.getContextPath() + "/categories/");
        }


    }
}

