package uma.taw.ubay.servlet.users;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.persistence.sessions.Session;
import uma.taw.ubay.SessionKeys;
import uma.taw.ubay.dao.ClientFacade;
import uma.taw.ubay.dao.ProductFacade;
import uma.taw.ubay.dao.ProductFavouritesFacade;
import uma.taw.ubay.entity.ClientEntity;
import uma.taw.ubay.entity.LoginCredentialsEntity;
import uma.taw.ubay.entity.ProductEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/users/products")
public class Products extends HttpServlet {
    @EJB
    ClientFacade clientFacade;

    @EJB
    ProductFavouritesFacade favouritesFacade;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {process(request, response);}

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {process(request,response);}

    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ClientEntity client = ((LoginCredentialsEntity) request.getSession().getAttribute(SessionKeys.LOGIN_CREDENTIALS)).getUser();

        List<ProductEntity> favouriteProducts = favouritesFacade.getClientFavouriteProducts(client);
        request.setAttribute("clientID", client.getId());
        request.setAttribute("favourite-products-list", favouriteProducts);
        request.getRequestDispatcher("products.jsp").forward(request,response);
    }
}

