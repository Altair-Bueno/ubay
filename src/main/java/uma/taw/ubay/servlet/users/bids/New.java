package uma.taw.ubay.servlet.users.bids;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uma.taw.ubay.SessionKeys;
import uma.taw.ubay.UsersKeys;
import uma.taw.ubay.dto.LoginDTO;
import uma.taw.ubay.service.BidService;

import java.io.IOException;

@WebServlet("/users/bids/new")
public class New extends HttpServlet {
    @EJB
    BidService service;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String referer = req.getHeader("Referer");
        var loginDTO = (LoginDTO) req.getSession().getAttribute(SessionKeys.LOGIN_DTO);

        String amountParameter = req.getParameter(UsersKeys.BID_AMOUNT_PARAMETER);
        String productIDParameter = req.getParameter(UsersKeys.BID_PRODUCT_ID_PARAMETER);

        service.createBid(loginDTO, amountParameter, productIDParameter);

        resp.sendRedirect(referer == null ? req.getContextPath() : referer);
    }

}
