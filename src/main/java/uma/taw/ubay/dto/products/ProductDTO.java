package uma.taw.ubay.dto.products;

import lombok.Value;

import java.util.Date;

@Value
public class ProductDTO {
    int id;
    String title;
    String description;
    double outPrice;
    String images;
    Date closeDate;
    Date publishDate;
    ProductClientDTO vendor;
    ProductCategoryDTO category;
}
