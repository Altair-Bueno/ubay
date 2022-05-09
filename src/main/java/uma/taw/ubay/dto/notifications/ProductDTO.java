package uma.taw.ubay.dto.notifications;

import lombok.Value;
import uma.taw.ubay.dto.products.CategoryDTO;
import uma.taw.ubay.dto.products.ClientDTO;

import java.sql.Date;

@Value
public class ProductDTO {
    int id;
    String title;
    String description;
    String images;
    Date closeDate;
}
