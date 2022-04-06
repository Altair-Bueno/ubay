package uma.taw.ubay.servlet.admin;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uma.taw.ubay.dao.ClientFacade;
import uma.taw.ubay.dao.LoginCredentialsFacade;
import uma.taw.ubay.entity.ClientEntity;
import uma.taw.ubay.entity.LoginCredentialsEntity;

import java.io.IOException;

@WebServlet("/admin/deleteUser")
public class DeleteUser extends HttpServlet {
    @EJB
    ClientFacade facadeClient;

    @EJB
    LoginCredentialsFacade facadeLogin;


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {process(request, response);}

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {process(request,response);}

    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        if(id != null){
            try{
                ClientEntity client = facadeClient.find(Integer.parseInt(id));
                LoginCredentialsEntity login = facadeLogin.searchClientLoginByClient(client);

                facadeLogin.remove(login);
                facadeClient.remove(client);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        request.getRequestDispatcher("deleteUser.jsp").forward(request,response);
    }
}
