package uma.taw.ubay.dto.users.bids;

import lombok.Value;

@Value
public class ProductDTO {
    int id;
    String title;
    VendorDTO vendor;
}
