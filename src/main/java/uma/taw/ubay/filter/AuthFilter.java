package uma.taw.ubay.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import uma.taw.ubay.SessionKeys;

import java.io.IOException;

/**
 * A HttpFilter that checks if the request has an active session on this server.
 * If the client doesn't have a session, the filter will redirect to the login
 * page
 */
public class AuthFilter extends HttpFilter {
    private final static String LOGIN_PAGE = "/auth/login";
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        if (session == null ||
                session.getAttribute(SessionKeys.LOGIN_DTO) == null) {
            res.sendRedirect(req.getContextPath() + LOGIN_PAGE);
        } else {
            chain.doFilter(req, res);
        }
    }
}
