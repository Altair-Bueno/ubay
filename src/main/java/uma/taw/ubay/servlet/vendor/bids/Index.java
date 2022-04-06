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
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet("/vendor/bids")
public class Index extends HttpServlet {
    @EJB
    BidFacade facade;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginCredentialsEntity loginCredentials = (LoginCredentialsEntity) req.getSession().getAttribute(SessionKeys.LOGIN_CREDENTIALS);
        String startDateParameter = req.getParameter("startDate");
        String endDateParameter = req.getParameter("endDate");
        // TODO complete filter
        try {
            Date startDate = null;
            Date endDate = null;
            Double minAmount = null;
            Double maxAmount = null;
            String productName = null;
            String clientName = null;
            Stream<BidEntity> bidsByClient = facade.filterBids(loginCredentials.getUser(),startDate,endDate,minAmount,maxAmount,productName,clientName);
            List<BidEntity> bidList = bidsByClient.collect(Collectors.toList());
            req.setAttribute(VendorKeys.BIDS_BY_VENDOR, bidList);
            req.getRequestDispatcher("/vendor/bids/index.jsp").forward(req,resp);
        } catch (Exception e) {
            resp.sendError(400,e.getMessage());
        }
    }
}
