package uma.taw.ubay.servlet.admin;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uma.taw.ubay.dao.ProductFacade;
import uma.taw.ubay.entity.ProductEntity;

import java.io.IOException;
import java.util.List;

//Not in use.
public class Products extends HttpServlet {
    @EJB
    ProductFacade facade;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
    }

    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String search = request.getParameter("search");
        List<ProductEntity> clientEntityList = facade.findAll();
        request.setAttribute("users-list", clientEntityList);

        request.getRequestDispatcher("users.jsp")
                .forward(request, response);
    }
}
