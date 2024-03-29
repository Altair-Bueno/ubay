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

/**
 * @author Altair Bueno
 */
@WebServlet("/users/bids")
public class Index extends HttpServlet {
    @EJB
    BidService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginDTO loginCredentials = (LoginDTO) req.getSession().getAttribute(SessionKeys.LOGIN_DTO);
        String startDateParameter = req.getParameter(UsersKeys.BID_START_DATE_PARAMETER);
        String endDateParameter = req.getParameter(UsersKeys.BID_END_DATE_PARAMETER);
        String productTitleParameter = req.getParameter(UsersKeys.BID_PRODUCT_TITLE_PARAMETER);
        String vendorNameParameter = req.getParameter(UsersKeys.BID_VENDOR_NAME_PARAMETER);
        String pageParameter = req.getParameter(UsersKeys.BID_PAGE_NUMBER_PARAMETER);
        var orderByParameter = req.getParameter(UsersKeys.ORDER_BY_PARAMETER);
        var ascParameter = req.getParameter(UsersKeys.ASC_PARAMETER);

        var bidList = service.getSentBids(loginCredentials, startDateParameter, endDateParameter, productTitleParameter, vendorNameParameter, pageParameter, orderByParameter, ascParameter);
        req.setAttribute(UsersKeys.BID_LIST, bidList);
        req.getRequestDispatcher("/users/bids/index.jsp").forward(req, resp);
    }
}
