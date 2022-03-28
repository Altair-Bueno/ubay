package uma.taw.ubay.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AuthFilter extends HttpFilter {

    private final static String LOGIN_PAGE = "auth/login";

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpSession session = req.getSession();
        Object entity = session.getAttribute("login");
        if (entity == null) {
            res.sendRedirect(LOGIN_PAGE);
        } else {
            chain.doFilter(req, res);
        }
    }
}
