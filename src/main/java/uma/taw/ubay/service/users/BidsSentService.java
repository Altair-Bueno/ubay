package uma.taw.ubay.service.users;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import uma.taw.ubay.dao.BidFacade;
import uma.taw.ubay.dao.LoginCredentialsFacade;
import uma.taw.ubay.dto.LoginDTO;
import uma.taw.ubay.dto.users.bids.BidDTO;
import uma.taw.ubay.dto.users.bids.ProductDTO;
import uma.taw.ubay.dto.users.bids.VendorDTO;
import uma.taw.ubay.entity.BidEntity;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Stateless
public class BidsSentService {
    @EJB
    BidFacade bidFacade;
    @EJB
    LoginCredentialsFacade loginCredentialsFacade;
    public List<BidDTO> getBids(LoginDTO loginDTO, String startDateParameter, String endDateParameter, String productTitleParameter, String vendorNameParameter, String pageParameter) {
        Date startDate = "".equals(startDateParameter) || startDateParameter == null ? null : Date.valueOf(startDateParameter);
        Date endDate = "".equals(endDateParameter) || endDateParameter == null ? null : Date.valueOf(endDateParameter);
        String productTitle = "".equals(productTitleParameter) ? null : productTitleParameter;
        String vendorName = "".equals(vendorNameParameter) ? null : vendorNameParameter;
        int page = "".equals(pageParameter) || pageParameter == null ? 0 : Integer.parseInt(pageParameter);
        if (page < 0) throw new IllegalArgumentException("Negative page index");

        var loginCredentials = loginCredentialsFacade.find(loginDTO.getUsername());
        Stream<BidEntity> bidEntityStream = bidFacade.getFilteredBidsFromUser(loginCredentials.getUser(), page, startDate, endDate, productTitle, vendorName);
        return bidEntityStream.map(x->new BidDTO(
                x.getPublishDate(),
                x.getAmount(),
                new ProductDTO(
                        x.getId(),
                        x.getProduct().getTitle(),
                        new VendorDTO(x.getProduct().getVendor().getName())
                )
        )).collect(Collectors.toList());
    }
}
