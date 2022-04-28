package uma.taw.ubay.service;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import lombok.NonNull;
import uma.taw.ubay.dao.BidFacade;
import uma.taw.ubay.dao.LoginCredentialsFacade;
import uma.taw.ubay.dto.LoginDTO;
import uma.taw.ubay.dto.users.bids.ProductDTO;
import uma.taw.ubay.dto.users.bids.SentBidsDTO;
import uma.taw.ubay.dto.users.bids.VendorDTO;
import uma.taw.ubay.dto.vendor.bids.ReceivedBidsDTO;
import uma.taw.ubay.dto.vendor.bids.UserDTO;
import uma.taw.ubay.entity.BidEntity;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Stateless
public class BidService {
    @EJB
    BidFacade bidFacade;
    @EJB
    LoginCredentialsFacade loginCredentialsFacade;

    private SentBidsDTO entityBidToSentBid(BidEntity bidEntity) {
        return new SentBidsDTO(
                bidEntity.getPublishDate(),
                bidEntity.getAmount(),
                new ProductDTO(
                        bidEntity.getId(),
                        bidEntity.getProduct().getTitle(),
                        new VendorDTO(bidEntity.getProduct().getVendor().getName())
                )
        );
    }

    private ReceivedBidsDTO entityBidToReceivedBid(BidEntity bidEntity) {
        return new ReceivedBidsDTO(
                bidEntity.getPublishDate(),
                bidEntity.getAmount(),
                new uma.taw.ubay.dto.vendor.bids.ProductDTO(bidEntity.getProduct().getId(), bidEntity.getProduct().getTitle()),
                new UserDTO(bidEntity.getUser().getName())
        );
    }

    public List<ReceivedBidsDTO> getReceivedBids(@NonNull LoginDTO loginDTO, String startDateParameter, String endDateParameter, String productTitleParameter, String clientNameParameter, String pageParameter) {
        Date startDate = "".equals(startDateParameter) || startDateParameter == null ? null : Date.valueOf(startDateParameter);
        Date endDate = "".equals(endDateParameter) || endDateParameter == null ? null : Date.valueOf(endDateParameter);
        String productTitle = "".equals(productTitleParameter) ? null : productTitleParameter;
        String clientName = "".equals(clientNameParameter) ? null : clientNameParameter;
        int page = "".equals(pageParameter) || pageParameter == null ? 0 : Integer.parseInt(pageParameter);
        if (page < 0) throw new IllegalArgumentException("Negative page index");

        var userCredentials = loginCredentialsFacade.find(loginDTO.getUsername());
        var vendor = userCredentials.getUser();
        Stream<BidEntity> bidEntityStream = bidFacade.getFilteredBidsFromVendor(vendor, page, startDate, endDate, productTitle, clientName);

        return bidEntityStream.map(this::entityBidToReceivedBid).collect(Collectors.toList());
    }
    public List<SentBidsDTO> getSentBids(@NonNull LoginDTO loginDTO, String startDateParameter, String endDateParameter, String productTitleParameter, String vendorNameParameter, String pageParameter) {
        Date startDate = "".equals(startDateParameter) || startDateParameter == null ? null : Date.valueOf(startDateParameter);
        Date endDate = "".equals(endDateParameter) || endDateParameter == null ? null : Date.valueOf(endDateParameter);
        String productTitle = "".equals(productTitleParameter) ? null : productTitleParameter;
        String vendorName = "".equals(vendorNameParameter) ? null : vendorNameParameter;
        int page = "".equals(pageParameter) || pageParameter == null ? 0 : Integer.parseInt(pageParameter);
        if (page < 0) throw new IllegalArgumentException("Negative page index");

        var loginCredentials = loginCredentialsFacade.find(loginDTO.getUsername());
        Stream<BidEntity> bidEntityStream = bidFacade.getFilteredBidsFromUser(loginCredentials.getUser(), page, startDate, endDate, productTitle, vendorName);
        return bidEntityStream.map(this::entityBidToSentBid).collect(Collectors.toList());
    }
}
