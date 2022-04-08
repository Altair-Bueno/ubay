package uma.taw.ubay.servlet.auth;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet in charge of invalidating the user current user session
 */
@WebServlet("/auth/signoff")
public class SignOff extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String referer = req.getHeader("Referer");
        HttpSession session = req.getSession(false);

        if (session == null) {
            resp.sendError(400,"The user is no longer log in");
        } else {
            session.invalidate();
            resp.sendRedirect(referer == null ? req.getContextPath() : referer);
        }
    }
}
