package uma.taw.ubay.dto.products;

import lombok.Value;

import java.util.List;

@Value
public class ProductsDTO {
    List<ProductDTO> productsList;
    int size;
}
