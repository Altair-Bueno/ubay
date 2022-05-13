package uma.taw.ubay.dto.bids;

import lombok.Value;

import java.sql.Timestamp;
/**
 * @author Altair Bueno
 */

@Value
public class ReceivedBidsDTO {
    Timestamp publishDate;
    double amount;
    ProductDTO product;
    UserDTO user;
}
