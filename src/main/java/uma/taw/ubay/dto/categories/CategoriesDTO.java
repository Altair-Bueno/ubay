package uma.taw.ubay.dto.categories;

import lombok.Value;

import java.util.List;

@Value
public class CategoriesDTO {
    List<CategoryDTO> userFavouriteCategories;
    List<CategoryDTO> categoryList;
    int userID;
}
