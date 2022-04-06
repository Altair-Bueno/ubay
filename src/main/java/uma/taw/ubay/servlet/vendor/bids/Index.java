package uma.taw.ubay.servlet.vendor.bids;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uma.taw.ubay.SessionKeys;
import uma.taw.ubay.VendorKeys;
import uma.taw.ubay.dao.BidFacade;
import uma.taw.ubay.entity.BidEntity;
import uma.taw.ubay.entity.LoginCredentialsEntity;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/vendor/bids")
public class Index extends HttpServlet {
    @EJB
    BidFacade facade;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginCredentialsEntity loginCredentials = (LoginCredentialsEntity) req.getSession().getAttribute(SessionKeys.LOGIN_CREDENTIALS);
        String startDateParameter = req.getParameter(VendorKeys.BID_START_DATE_PARAMETER);
        String endDateParameter = req.getParameter(VendorKeys.BID_END_DATE_PARAMETER);
        String productTitleParameter = req.getParameter(VendorKeys.BID_PRODUCT_TITLE_PARAMETER);
        String clientNameParameter = req.getParameter(VendorKeys.BID_CLIENT_NAME_PARAMETER);

        try {
            Date startDate = "".equals(startDateParameter) || startDateParameter == null ? null : Date.valueOf(startDateParameter);
            Date endDate = "".equals(endDateParameter) || endDateParameter == null ? null: Date.valueOf(endDateParameter);
            String productTitle = "".equals(productTitleParameter) ? null : productTitleParameter;
            String clientName = "".equals(clientNameParameter) ? null : clientNameParameter;

            List<BidEntity> bidList = facade.filterBids(loginCredentials.getUser(),startDate,endDate, productTitle,clientName);
            req.setAttribute(VendorKeys.BID_LIST, bidList);
            req.getRequestDispatcher("/vendor/bids/index.jsp").forward(req,resp);
        } catch (Exception e) {
            resp.sendError(400,e.getMessage());
        }
    }
}
