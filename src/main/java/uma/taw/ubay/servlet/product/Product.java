package uma.taw.ubay.servlet.product;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uma.taw.ubay.SessionKeys;
import uma.taw.ubay.dao.ProductFacade;
import uma.taw.ubay.dao.ProductFavouritesFacade;
import uma.taw.ubay.dto.products.LoginDTO;
import uma.taw.ubay.dto.products.ProductDTO;
import uma.taw.ubay.service.products.ProductService;

import java.io.IOException;
import java.util.List;

@WebServlet("/product/item")
public class Product extends HttpServlet {
    @EJB
    ProductService productService;

    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var loginDTO = (LoginDTO) req.getSession().getAttribute(SessionKeys.LOGIN_DTO);

        Integer id = Integer.parseInt(req.getParameter("id"));
        ProductDTO productDTO = productService.findProduct(id);
        boolean isUserFav = productService.isProductUserFavourite(loginDTO, id);


        req.setAttribute("product", productDTO);
        req.setAttribute("isFav", isUserFav);
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
