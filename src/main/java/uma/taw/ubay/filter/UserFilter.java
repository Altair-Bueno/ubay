package uma.taw.ubay.filter;

import jakarta.ejb.EJB;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import uma.taw.ubay.SessionKeys;
import uma.taw.ubay.dto.LoginDTO;
import uma.taw.ubay.dto.products.ProductClientDTO;
import uma.taw.ubay.service.products.ProductService;

import java.io.IOException;

/**
 * @author Francisco Javier Hernández
 */

public class UserFilter extends HttpFilter {

    @EJB
    ProductService productService;

    private final static String INDEX = "/product";

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        var loginDTO = (LoginDTO) req.getSession().getAttribute(SessionKeys.LOGIN_DTO);
        ProductClientDTO cliente = loginDTO == null ? null : productService.loginDTOtoClientDTO(loginDTO); // Null if admin or not logged in

        if(cliente == null){
            res.sendRedirect(req.getContextPath() + INDEX);
        } else{
            chain.doFilter(req, res);
        }
    }
}
