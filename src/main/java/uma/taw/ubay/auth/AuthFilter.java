package uma.taw.ubay.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import uma.taw.ubay.SessionKeys;

import java.io.IOException;

public class AuthFilter extends HttpFilter {

    // warning: Probably dangerous redirect
    private final static String LOGIN_PAGE = "auth/login";

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        if (session == null ||
                session.getAttribute(SessionKeys.LOGIN_CREDENTIALS) == null) {
            res.sendRedirect(LOGIN_PAGE);
        } else {
            chain.doFilter(req, res);
        }
    }
}
