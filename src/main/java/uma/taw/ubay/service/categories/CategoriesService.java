package uma.taw.ubay.service.categories;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import org.jetbrains.annotations.NotNull;
import uma.taw.ubay.UbayException;
import uma.taw.ubay.dao.CategoryFacade;
import uma.taw.ubay.dao.ClientFacade;
import uma.taw.ubay.dao.UserFavouritesFacade;
import uma.taw.ubay.dto.LoginDTO;
import uma.taw.ubay.dto.categories.CategoriesDTO;
import uma.taw.ubay.dto.categories.CategoryDTO;
import uma.taw.ubay.dto.users.ClientDTO;
import uma.taw.ubay.entity.CategoryEntity;
import uma.taw.ubay.entity.ClientEntity;
import uma.taw.ubay.entity.KindEnum;
import uma.taw.ubay.entity.UserFavouritesEntity;
import uma.taw.ubay.service.AuthService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class CategoriesService {

    @EJB
    CategoryFacade categoryFacade;

    @EJB
    ClientFacade clientFacade;

    @EJB
    UserFavouritesFacade favouritesFacade;

    @EJB
    AuthService authService;

    public void addCategory(String name, String description) {
        if(name != null && description != null){
            CategoryEntity category = new CategoryEntity();
            category.setName(name);
            category.setDescription(description);
            categoryFacade.create(category);
        }
    }

    public void addFavouriteCategory(String clientID, String categoryID) {
        ClientEntity client = clientFacade.find(Integer.parseInt(clientID));
        CategoryEntity category = categoryFacade.find(Integer.parseInt(categoryID));

        UserFavouritesEntity favourite = new UserFavouritesEntity();
        favourite.setCategory(category);
        favourite.setUser(client);
        favouritesFacade.create(favourite);
    }

    @NotNull
    private List<CategoryDTO> userFavouriteCategories(LoginDTO login) {
        if(login != null){
            ClientEntity client = authService.getCredentialsEntity(login).getUser();
            List<CategoryEntity> userFavouriteCategories = favouritesFacade.getClientFavouriteCategories(client);
            return userFavouriteCategories.stream().map(this::categoryEntityToDTO).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    @NotNull
    private List<CategoryDTO> categories(){
        return categoryFacade.findAll().stream().map(this::categoryEntityToDTO).collect(Collectors.toList());
    }

    private CategoryDTO categoryEntityToDTO(CategoryEntity category){
        return new CategoryDTO(category.getId(), category.getName(), category.getDescription());
    }

    private ClientEntity getClientEntity(LoginDTO login){
        return authService.getCredentialsEntity(login).getUser();
    }

    public void deleteCategory(String id) {
        if(id != null){
            try{
                CategoryEntity category = categoryFacade.find(Integer.parseInt(id));
                categoryFacade.remove(category);
            } catch (Exception e){
                throw new UbayException("Cannot delete a category that is being used by a product.");
            }
        }
    }

    public void deleteFavourite(String clientID, String categoryID) {
        ClientEntity client = clientFacade.find(Integer.parseInt(clientID));
        CategoryEntity category = categoryFacade.find(Integer.parseInt(categoryID));

        UserFavouritesEntity favourite = new UserFavouritesEntity();
        favourite.setCategory(category);
        favourite.setUser(client);
        favouritesFacade.remove(favourite);
    }

    public void modify(String id, String name, String description) {
        CategoryEntity category = categoryFacade.find(Integer.parseInt(id));

        category.setName(name);
        category.setDescription(description);

        categoryFacade.edit(category);
    }

    @NotNull
    public CategoriesDTO processCategories(LoginDTO loginDTO) {
        List<CategoryDTO> userFavouriteCategories = new ArrayList<>();
        List<CategoryDTO> categoryList = categories();
        int userID = -1;

        if(loginDTO.getKind().equals(KindEnum.client)){
            userFavouriteCategories = userFavouriteCategories(loginDTO);
            userID = getClientEntity(loginDTO).getId();
        }

        return new CategoriesDTO(userFavouriteCategories, categoryList, userID);
    }
}
