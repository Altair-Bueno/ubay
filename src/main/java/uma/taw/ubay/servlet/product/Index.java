package uma.taw.ubay.servlet.product;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uma.taw.ubay.dao.CategoryFacade;
import uma.taw.ubay.dao.ProductFacade;
import uma.taw.ubay.entity.CategoryEntity;
import uma.taw.ubay.entity.ProductEntity;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/product")
public class Index extends HttpServlet {
    @EJB
    ProductFacade facade;

    @EJB
    CategoryFacade categoryFacade;

    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("category-list", categoryFacade.findAll());
        req.setAttribute("product-tam", facade.findAll().size());
        var page = req.getParameter("page");
        req.setAttribute("product-list", facade.getByPage(page == null ? 0 : Integer.parseInt(page) - 1));

        // Filters:
        String productName = req.getParameter("name");
        String category = req.getParameter("category");

        if(productName != null || (category != null && !category.equals("--"))){
            CategoryEntity cat = null;
            if(!category.equals("--")){
                cat = categoryFacade.searchById(category);
            }
            List<ProductEntity> productosFiltrados = facade.filter(productName, cat);
            req.setAttribute("product-list", productosFiltrados);
        }
        req.getRequestDispatcher("product/index.jsp").forward(req,resp);
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
