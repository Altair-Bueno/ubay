package uma.taw.ubay.servlet.product;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uma.taw.ubay.SessionKeys;
import uma.taw.ubay.dto.LoginDTO;
import uma.taw.ubay.dto.products.ProductClientDTO;
import uma.taw.ubay.dto.products.ProductsDTO;
import uma.taw.ubay.service.products.ProductService;

import java.io.IOException;

@WebServlet("/product")
public class Index extends HttpServlet {

    @EJB
    ProductService productService;

    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var loginDTO = (LoginDTO) req.getSession().getAttribute(SessionKeys.LOGIN_DTO);
        ProductClientDTO cliente = loginDTO == null ? null : productService.loginDTOtoClientDTO(loginDTO);

        Object categoryparameter = req.getParameter("category");

        String productName = req.getParameter("name") == null ? "" : req.getParameter("name");
        String category = (categoryparameter == null || ((String) categoryparameter).equals("--") ? "0" : req.getParameter("category"));
        String page = req.getParameter("page") == null ? "1" : req.getParameter("page");
        String favOwnedFilter = req.getParameter("favOwnedFilter") == null ? "" : req.getParameter("favOwnedFilter");

        page = page == null ? "1" : page;

        ProductsDTO productDTOS = productService.getProductsList(cliente, productName, category, favOwnedFilter, page);

        req.setAttribute("category-list", productService.categories());
        req.setAttribute("categoryFilter", Integer.parseInt(category));
        req.setAttribute("nameFilter", productName);
        req.setAttribute("product-tam", productDTOS.getSize());
        req.setAttribute("product-list", productDTOS.getProductsList());
        req.setAttribute("user", cliente != null);

        req.getRequestDispatcher("product/index.jsp").forward(req, resp);
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
