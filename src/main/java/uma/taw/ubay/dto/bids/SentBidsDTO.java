package uma.taw.ubay.dto.bids;

import lombok.Value;

import java.sql.Timestamp;

@Value
public class SentBidsDTO {
    Timestamp publishDate;
    double amount;
    ProductDTO product;
}
