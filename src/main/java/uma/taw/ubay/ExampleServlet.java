package uma.taw.ubay;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uma.taw.ubay.dao.LoginCredentialsFacade;

import java.io.IOException;

@WebServlet("/example")
public class ExampleServlet extends HttpServlet {
    @EJB
    LoginCredentialsFacade facade;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {process(request, response);}

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {process(request,response);}

    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("example",facade.findAll());
        request.getRequestDispatcher("example.jsp")
                .forward(request,response);
    }
}