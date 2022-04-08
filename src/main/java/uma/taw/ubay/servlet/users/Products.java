package uma.taw.ubay.servlet.users;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uma.taw.ubay.dao.ClientFacade;
import uma.taw.ubay.dao.ProductFacade;
import uma.taw.ubay.entity.ClientEntity;
import uma.taw.ubay.entity.ProductEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/users/products")
public class Products extends HttpServlet {
    @EJB
    ClientFacade clientFacade;

    @EJB
    ProductFacade productFacade;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {process(request, response);}

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {process(request,response);}

    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        ClientEntity client = clientFacade.find(Integer.parseInt(id));

        List<ProductEntity> uploadedProducts = productFacade.uploadedProducts(client);
        request.setAttribute("uploaded-products-list", uploadedProducts);

        request.getRequestDispatcher("products.jsp").forward(request,response);
    }
}

