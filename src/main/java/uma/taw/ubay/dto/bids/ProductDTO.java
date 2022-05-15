package uma.taw.ubay.dto.bids;

import lombok.Value;
/**
 * @author Altair Bueno
 */

@Value
public class ProductDTO {
    int id;
    String title;
    VendorDTO vendor;
}
