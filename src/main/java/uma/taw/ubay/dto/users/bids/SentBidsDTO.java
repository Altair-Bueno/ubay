package uma.taw.ubay.dto.users.bids;

import lombok.Value;

import java.sql.Timestamp;

@Value
public class SentBidsDTO {
    Timestamp publishDate;
    double amount;
    ProductDTO product;
}
