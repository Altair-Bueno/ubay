package uma.taw.ubay.dto.notifications;

import lombok.Value;

import java.sql.Timestamp;

/**
 * @author Francisco Javier Hernández
 */

@Value
public class BidsDTO {
    Timestamp publishDate;
    double amount;
    ProductDTO product;
}
