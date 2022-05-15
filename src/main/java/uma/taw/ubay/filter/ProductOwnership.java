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
import uma.taw.ubay.dto.LoginDTO;
import uma.taw.ubay.entity.KindEnum;
import uma.taw.ubay.entity.ProductEntity;
import uma.taw.ubay.service.AuthService;

import java.io.IOException;

/**
 * @author Francisco Javier Hernández
 */

public class ProductOwnership extends HttpFilter {
    @EJB
    ProductFacade productFacade;
    @EJB
    AuthService authService;

    private final static String INDEX = "/product";

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        ProductEntity p = productFacade.find(Integer.parseInt(req.getParameter("id")));
        var loginDTO = (LoginDTO) session.getAttribute(SessionKeys.LOGIN_DTO);
        var loginCredentialsEntity = authService.getCredentialsEntity(loginDTO);

        if (!p.getVendor().equals(loginCredentialsEntity.getUser()) && !loginCredentialsEntity.getKind().equals(KindEnum.admin)) {
            res.sendRedirect(req.getContextPath() + INDEX);
        } else {
            chain.doFilter(req, res);
        }
    }
}
