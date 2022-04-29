package uma.taw.ubay.servlet.users;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uma.taw.ubay.service.users.UsersService;

import java.io.IOException;

@WebServlet("/users/addFavourite")
public class AddFavProduct extends HttpServlet {
    @EJB
    UsersService usersService;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {process(request, response);}

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {process(request,response);}

    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String productID = request.getParameter("productID");
        String clientID = request.getParameter("clientID");

        usersService.addFavProduct(productID, clientID);

        request.getRequestDispatcher("addFavProduct.jsp").forward(request,response);
    }


}

