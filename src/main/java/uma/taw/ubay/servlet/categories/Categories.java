package uma.taw.ubay.servlet.categories;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uma.taw.ubay.dao.CategoryFacade;
import uma.taw.ubay.dao.UserFavouritesFacade;
import uma.taw.ubay.entity.CategoryEntity;
import uma.taw.ubay.entity.ClientEntity;
import uma.taw.ubay.entity.UserFavouritesEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet("/categories")
public class Categories extends HttpServlet {
    @EJB
    CategoryFacade facade;

    @EJB
    UserFavouritesFacade favouritesFacade;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {process(request, response);}

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {process(request,response);}

    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<CategoryEntity> catList = facade.findAll();
        HashMap<ClientEntity, List<CategoryEntity>> favMap = new HashMap<>();

        for(UserFavouritesEntity fav : favouritesFacade.findAll()){
            if(favMap.get(fav.getUser()) != null){
                List<CategoryEntity> list = favMap.get(fav.getUser());
                list.add(fav.getCategory());
                favMap.put(fav.getUser(),list);
            } else {
                List<CategoryEntity> list = new ArrayList<>();
                list.add(fav.getCategory());
                favMap.put(fav.getUser(),list);
            }
        }
        request.setAttribute("favMap", favMap);
        request.setAttribute("category-list", catList);
        request.getRequestDispatcher("categories.jsp").forward(request,response);
    }
}
