package uma.taw.ubay.filter;

import jakarta.ejb.EJB;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import uma.taw.ubay.SessionKeys;
import uma.taw.ubay.dao.ProductFacade;
import uma.taw.ubay.entity.LoginCredentialsEntity;
import uma.taw.ubay.entity.ProductEntity;

import java.io.IOException;

public class ProductOwnership extends HttpFilter {
    @EJB
    ProductFacade facade;

    private final static String PRODUCTS_LIST = "/product/productslist";

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        ProductEntity p = facade.find(Integer.parseInt(req.getParameter("id")));
        LoginCredentialsEntity login = (LoginCredentialsEntity) session.getAttribute(SessionKeys.LOGIN_CREDENTIALS);

        if(!p.getVendor().equals(login.getUser())){
            res.sendRedirect(req.getContextPath() + PRODUCTS_LIST);
        } else{
            chain.doFilter(req, res);
        }
    }
}
