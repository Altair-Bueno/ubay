package uma.taw.ubay.dto.products;

import lombok.Value;

import java.util.List;

/**
 * @author Francisco Javier Hernández
 */

@Value
public class ProductsDTO {
    List<ProductDTO> productsList;
    int size;
}
