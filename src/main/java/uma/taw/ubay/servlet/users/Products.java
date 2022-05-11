package uma.taw.ubay.servlet.users;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uma.taw.ubay.SessionKeys;
import uma.taw.ubay.dto.LoginDTO;
import uma.taw.ubay.dto.users.ProductDTO;
import uma.taw.ubay.service.users.UsersService;
import java.io.IOException;
import java.util.List;

/**
 * @author José Luis Bueno Pachón
 */

@WebServlet("/users/products")
public class Products extends HttpServlet {
    @EJB
    UsersService usersService;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {process(request, response);}

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {process(request,response);}

    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        var login = ((LoginDTO) request.getSession().getAttribute(SessionKeys.LOGIN_DTO));

        List<ProductDTO> favouriteProducts = usersService.products(login);

        request.setAttribute("clientID", usersService.getClientID(login));
        request.setAttribute("favourite-products-list", favouriteProducts);
        request.getRequestDispatcher("products.jsp").forward(request,response);
    }
}

