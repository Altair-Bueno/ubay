package uma.taw.ubay;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uma.taw.ubay.dao.LoginCredentialsFacade;
import uma.taw.ubay.entity.KindEnum;
import uma.taw.ubay.entity.LoginCredentialsEntity;

import java.io.IOException;

@WebServlet("/newUser")
public class NewUser extends HttpServlet {
    @EJB
    LoginCredentialsFacade facade;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {process(request, response);}

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {process(request,response);}

    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        LoginCredentialsEntity entity = new LoginCredentialsEntity();
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        entity.setUsername(username);
        entity.setPassword(password);
        entity.setKind(KindEnum.admin);
        facade.create(entity);
        response.sendRedirect("listCredentials");
    }
}