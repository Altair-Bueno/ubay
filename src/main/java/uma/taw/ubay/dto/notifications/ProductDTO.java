package uma.taw.ubay.dto.notifications;

import lombok.Value;

import java.sql.Date;

/**
 * @author Francisco Javier Hernández
 */

@Value
public class ProductDTO {
    int id;
    String title;
    String description;
    String images;
    Date closeDate;
}
