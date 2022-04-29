package uma.taw.ubay.service.categories;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import org.jetbrains.annotations.NotNull;
import uma.taw.ubay.dao.CategoryFacade;
import uma.taw.ubay.dao.ClientFacade;
import uma.taw.ubay.dao.UserFavouritesFacade;
import uma.taw.ubay.dto.categories.CategoryDTO;
import uma.taw.ubay.dto.users.ClientDTO;
import uma.taw.ubay.entity.CategoryEntity;
import uma.taw.ubay.entity.ClientEntity;
import uma.taw.ubay.entity.UserFavouritesEntity;

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
    public CategoryDTO categories() {
        List<CategoryEntity> catList = categoryFacade.findAll();
        HashMap<ClientEntity, List<CategoryEntity>> favMap = new HashMap<>();

        for(UserFavouritesEntity fav : favouritesFacade.findAll()){
            if(favMap.get(fav.getUser()) != null){
                List<CategoryEntity> list = favMap.get(fav.getUser());
                list.add(fav.getCategory());
                favMap.put(fav.getUser(),list);
            } else {
                List<CategoryEntity> list = new ArrayList<>();
                list.add(fav.getCategory());
                //var client = transform(fav.getUser());
                favMap.put(fav.getUser(),list);
            }
        }

        var catListTransformed = catList.stream().map(x -> new CategoryDTO()).collect(Collectors.toList());

        //var dto = new CategoriesDTO(catListTransformed, favMap);
        return null;
    }

    /*
    private ClientDTO transform(ClientEntity client){

    }

     */
}
