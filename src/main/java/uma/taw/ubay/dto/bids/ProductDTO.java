package uma.taw.ubay.dto.bids;

import lombok.Value;

@Value
public class ProductDTO {
    int id;
    String title;
    VendorDTO vendor;
}
