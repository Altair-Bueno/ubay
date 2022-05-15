package uma.taw.ubay.servlet.product;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uma.taw.ubay.SessionKeys;
import uma.taw.ubay.dto.LoginDTO;
import uma.taw.ubay.dto.products.ProductBidDTO;
import uma.taw.ubay.dto.products.ProductClientDTO;
import uma.taw.ubay.dto.products.ProductDTO;
import uma.taw.ubay.entity.KindEnum;
import uma.taw.ubay.service.products.ProductService;

import java.io.IOException;

@WebServlet("/product/item")
public class Product extends HttpServlet {
    @EJB
    ProductService productService;

    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var loginDTO = (LoginDTO) req.getSession().getAttribute(SessionKeys.LOGIN_DTO);
        ProductClientDTO cliente = loginDTO == null ? null : productService.loginDTOtoClientDTO(loginDTO);


        Integer id = Integer.parseInt(req.getParameter("id"));
        ProductDTO productDTO = productService.findProduct(id);
        ProductBidDTO highestBid = productService.getHighestBid(id);

        if (cliente == null) {
            req.setAttribute("isFav", null);
        } else {
            boolean isUserFav = productService.isProductUserFavourite(cliente, id);
            req.setAttribute("isFav", isUserFav);
        }

        req.setAttribute("user", cliente);
        req.setAttribute("product", productDTO);
        req.setAttribute("highestBid", highestBid);
        req.setAttribute("isAdmin", loginDTO.getKind().equals(KindEnum.admin));

        req.getRequestDispatcher("item.jsp").forward(req, resp);
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
