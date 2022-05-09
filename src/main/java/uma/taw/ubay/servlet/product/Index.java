package uma.taw.ubay.servlet.product;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uma.taw.ubay.dto.products.*;
import uma.taw.ubay.service.products.ProductService;

import java.io.IOException;

@WebServlet("/product")
public class Index extends HttpServlet {

    @EJB
    ProductService productService;

    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productName = req.getParameter("name");
        String category = req.getParameter("category");
        String page = req.getParameter("page");

        ProductsDTO productDTOS = productService.getProductsList(productName, category, page);

        req.setAttribute("category-list", productService.categories());
        req.setAttribute("product-tam", productDTOS.getSize());
        req.setAttribute("product-list", productDTOS.getProductsList());

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
