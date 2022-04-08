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
import uma.taw.ubay.dao.ProductFacade;
import uma.taw.ubay.entity.BidEntity;
import uma.taw.ubay.entity.LoginCredentialsEntity;
import uma.taw.ubay.entity.ProductEntity;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;

@WebServlet("/users/bids/new")
public class New extends HttpServlet {
    @EJB
    ProductFacade productFacade;
    @EJB
    BidFacade bidFacade;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String referer = req.getHeader("Referer");
        LoginCredentialsEntity credentials = (LoginCredentialsEntity) req.getSession().getAttribute(SessionKeys.LOGIN_CREDENTIALS);
        String amountParameter = req.getParameter(UsersKeys.BID_AMOUNT_PARAMETER);
        String productIDParameter = req.getParameter(UsersKeys.BID_PRODUCT_ID_PARAMETER);
        Timestamp timestamp = new Timestamp(Instant.now().getEpochSecond());

        try {
            double amount = Double.parseDouble(amountParameter);
            int productId = Integer.parseInt(productIDParameter);
            ProductEntity product = productFacade.find(productId);

            if (product == null) throw new IllegalArgumentException("The given product ID doesn't exist");
            if (!product.isCurrentlyAvailable()) throw new IllegalArgumentException("The given product is no longer available");
            if (product.getOutPrice() > amount) throw new IllegalArgumentException("The received amount is lower than the starting bid");

            BidEntity highestBid = bidFacade.getHighestBidByProduct(product);
            if (highestBid != null && highestBid.getAmount() >= amount)
                throw new IllegalArgumentException("A higher bid exist. Current bid amount: " + highestBid.getAmount());

            BidEntity bid = new BidEntity(timestamp,amount,product,credentials.getUser());
            bidFacade.create(bid);

            resp.sendRedirect(referer == null ? req.getContextPath() : referer);
        }catch (IllegalArgumentException e) {
            resp.sendError(400,e.getMessage());
        }
    }
}
