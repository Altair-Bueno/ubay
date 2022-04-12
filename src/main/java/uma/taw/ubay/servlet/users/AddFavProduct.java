package uma.taw.ubay.servlet.users;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uma.taw.ubay.dao.ClientFacade;
import uma.taw.ubay.dao.ProductFacade;
import uma.taw.ubay.dao.ProductFavouritesFacade;
import uma.taw.ubay.entity.ClientEntity;
import uma.taw.ubay.entity.ProductEntity;
import uma.taw.ubay.entity.ProductFavouritesEntity;

import java.io.IOException;

@WebServlet("/users/addFavourite")
public class AddFavProduct extends HttpServlet {
    @EJB
    ProductFavouritesFacade favouritesFacade;

    @EJB
    ClientFacade clientFacade;

    @EJB
    ProductFacade productFacade;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {process(request, response);}

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {process(request,response);}

    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String productID = request.getParameter("productID");
        String clientID = request.getParameter("clientID");

        ProductEntity product = productFacade.find(Integer.parseInt(productID));
        ClientEntity client = clientFacade.find(Integer.parseInt(clientID));

        ProductFavouritesEntity fav = favouritesFacade.getTuple(client, product);
        favouritesFacade.create(fav);

        request.getRequestDispatcher("addFavProduct.jsp").forward(request,response);
    }
}

