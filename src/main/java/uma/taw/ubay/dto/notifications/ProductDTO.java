package uma.taw.ubay.dto.notifications;

import lombok.Value;

import java.sql.Date;

@Value
public class ProductDTO {
    int id;
    String title;
    String description;
    String images;
    Date closeDate;
}
