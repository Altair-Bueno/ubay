package uma.taw.ubay.dto.products;

import lombok.Value;

@Value
public class ProductFavouritesDTO {
    ProductDTO product;
    ClientDTO user;
}
