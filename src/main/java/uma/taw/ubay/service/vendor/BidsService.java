package uma.taw.ubay.service.vendor;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import lombok.NonNull;
import uma.taw.ubay.dao.BidFacade;
import uma.taw.ubay.dao.LoginCredentialsFacade;
import uma.taw.ubay.dto.auth.LoginDTO;
import uma.taw.ubay.dto.vendor.BidDTO;
import uma.taw.ubay.dto.vendor.ProductDTO;
import uma.taw.ubay.dto.vendor.UserDTO;
import uma.taw.ubay.entity.BidEntity;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Stateless
public class BidsService {
    @EJB
    BidFacade bidFacade;
    @EJB
    LoginCredentialsFacade loginCredentialsFacade;
    public List<BidDTO> getBids(@NonNull LoginDTO loginDTO, String startDateParameter, String endDateParameter, String productTitleParameter, String clientNameParameter, String pageParameter) {
        Date startDate = "".equals(startDateParameter) || startDateParameter == null ? null : Date.valueOf(startDateParameter);
        Date endDate = "".equals(endDateParameter) || endDateParameter == null ? null : Date.valueOf(endDateParameter);
        String productTitle = "".equals(productTitleParameter) ? null : productTitleParameter;
        String clientName = "".equals(clientNameParameter) ? null : clientNameParameter;
        int page = "".equals(pageParameter) || pageParameter == null ? 0 : Integer.parseInt(pageParameter);
        if (page < 0) throw new IllegalArgumentException("Negative page index");

        var userCredentials = loginCredentialsFacade.find(loginDTO.getUsername());
        var vendor = userCredentials.getUser();
        Stream<BidEntity> bidEntityStream = bidFacade.getFilteredBidsFromVendor(vendor, page, startDate, endDate, productTitle, clientName);

        return bidEntityStream.map(
                (BidEntity bidEntity) -> new BidDTO(
                        bidEntity.getPublishDate(),
                        bidEntity.getAmount(),
                        new ProductDTO(bidEntity.getProduct().getId(),bidEntity.getProduct().getTitle()),
                        new UserDTO(bidEntity.getUser().getName())
                )).collect(Collectors.toList());
    }
}
