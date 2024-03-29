package uma.taw.ubay.dto.users;

import lombok.Value;

import java.sql.Date;

/**
 * @author José Luis Bueno Pachón
 */

@Value
public class ProductDTO {
    int id;
    String title;
    String description;
    String images;
    Date closeDate;
}
