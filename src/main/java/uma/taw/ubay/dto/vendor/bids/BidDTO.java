package uma.taw.ubay.dto.vendor.bids;

import lombok.Value;

import java.sql.Timestamp;

@Value
public class BidDTO {
    Timestamp publishDate;
    double amount;
    ProductDTO product;
    UserDTO user;
}
