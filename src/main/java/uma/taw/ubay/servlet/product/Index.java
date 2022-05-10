package uma.taw.ubay.servlet.product;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uma.taw.ubay.SessionKeys;
import uma.taw.ubay.dto.LoginDTO;
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
        page = page == null ? "1" : page;
        var loginDTO = (LoginDTO) req.getSession().getAttribute(SessionKeys.LOGIN_DTO);

        ProductsDTO productDTOS = productService.getProductsList(productName, category, page);

        req.setAttribute("category-list", productService.categories());
        req.setAttribute("categoryFilter", (category == null || category.equals("--")) ? 0 : Integer.parseInt(category));
        req.setAttribute("nameFilter", productName);
        req.setAttribute("product-tam", productDTOS.getSize());
        req.setAttribute("product-list", productDTOS.getProductsList());
        req.setAttribute("user", loginDTO != null);

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
