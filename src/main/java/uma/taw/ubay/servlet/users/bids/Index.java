package uma.taw.ubay.servlet.users.bids;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uma.taw.ubay.SessionKeys;
import uma.taw.ubay.UsersKeys;
import uma.taw.ubay.dao.BidFacade;
import uma.taw.ubay.entity.BidEntity;
import uma.taw.ubay.entity.LoginCredentialsEntity;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/users/bids")
public class Index extends HttpServlet {
    @EJB
    BidFacade facade;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginCredentialsEntity loginCredentials = (LoginCredentialsEntity) req.getSession().getAttribute(SessionKeys.LOGIN_CREDENTIALS);
        String startDateParameter = req.getParameter(UsersKeys.BID_START_DATE_PARAMETER);
        String endDateParameter = req.getParameter(UsersKeys.BID_END_DATE_PARAMETER);
        String productTitleParameter = req.getParameter(UsersKeys.BID_PRODUCT_TITLE_PARAMETER);
        String vendorNameParameter = req.getParameter(UsersKeys.BID_VENDOR_NAME_PARAMETER);
        String pageParameter = req.getParameter(UsersKeys.BID_PAGE_NUMBER_PARAMETER);

        Date startDate = "".equals(startDateParameter) || startDateParameter == null ? null : Date.valueOf(startDateParameter);
        Date endDate = "".equals(endDateParameter) || endDateParameter == null ? null : Date.valueOf(endDateParameter);
        String productTitle = "".equals(productTitleParameter) ? null : productTitleParameter;
        String vendorName = "".equals(vendorNameParameter) ? null : vendorNameParameter;
        int page = "".equals(pageParameter) || pageParameter == null ? 0 : Integer.parseInt(pageParameter);
        if (page < 0) throw new IllegalArgumentException("Negative page index");

        List<BidEntity> bidList = facade.getFilteredBidsFromUser(loginCredentials.getUser(), page, startDate, endDate, productTitle, vendorName);
        req.setAttribute(UsersKeys.BID_LIST, bidList);
        req.getRequestDispatcher("/users/bids/index.jsp").forward(req, resp);
    }
}
