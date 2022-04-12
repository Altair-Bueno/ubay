package uma.taw.ubay.servlet.product;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uma.taw.ubay.dao.ProductFacade;
import uma.taw.ubay.entity.ProductEntity;

import java.io.IOException;

@WebServlet("/product/item")
public class Product extends HttpServlet {
    @EJB
    ProductFacade facade;

    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        ProductEntity p = facade.find(id);
        req.setAttribute("product", p);
        req.getRequestDispatcher("item.jsp").forward(req,resp);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }
}
