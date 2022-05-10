package uma.taw.ubay.dto.products;

import lombok.Value;

/**
 * @author Francisco Javier Hernández
 */

@Value
public class ProductFavouritesDTO {
    ProductDTO product;
    ProductClientDTO user;
}
