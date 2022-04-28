package uma.taw.ubay.servlet.vendor.bids;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uma.taw.ubay.SessionKeys;
import uma.taw.ubay.VendorKeys;
import uma.taw.ubay.dto.LoginDTO;
import uma.taw.ubay.dto.vendor.bids.ReceivedBidsDTO;
import uma.taw.ubay.service.BidService;

import java.io.IOException;
import java.util.List;

@WebServlet("/vendor/bids")
public class Index extends HttpServlet {
    @EJB
    BidService service;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginDTO loginDTO = (LoginDTO) req.getSession().getAttribute(SessionKeys.LOGIN_DTO);
        String startDateParameter = req.getParameter(VendorKeys.BID_START_DATE_PARAMETER);
        String endDateParameter = req.getParameter(VendorKeys.BID_END_DATE_PARAMETER);
        String productTitleParameter = req.getParameter(VendorKeys.BID_PRODUCT_TITLE_PARAMETER);
        String clientNameParameter = req.getParameter(VendorKeys.BID_CLIENT_NAME_PARAMETER);
        String pageParameter = req.getParameter(VendorKeys.BID_PAGE_NUMBER_PARAMETER);

        List<ReceivedBidsDTO> bidList = service.getReceivedBids(loginDTO, startDateParameter, endDateParameter, productTitleParameter, clientNameParameter, pageParameter);
        req.setAttribute(VendorKeys.BID_LIST, bidList);
        req.getRequestDispatcher("/vendor/bids/index.jsp").forward(req, resp);
    }

}
