package uma.taw.ubay.dto.products;

import lombok.Value;
import uma.taw.ubay.entity.KindEnum;

/**
 * @author Francisco Javier Hernández
 */

@Value
public class ProductClientDTO {
    int id;
    KindEnum kind;
}
