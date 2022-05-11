package uma.taw.ubay.servlet.users;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.postgresql.util.PSQLException;
import uma.taw.ubay.UbayException;
import uma.taw.ubay.dao.ClientFacade;
import uma.taw.ubay.dao.LoginCredentialsFacade;
import uma.taw.ubay.entity.ClientEntity;
import uma.taw.ubay.entity.LoginCredentialsEntity;
import uma.taw.ubay.service.users.UsersService;

import java.io.IOException;

@WebServlet("/users/delete")
public class Delete extends HttpServlet {
    @EJB
    UsersService usersService;


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {process(request, response);}

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {process(request,response);}

    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        try{
            usersService.deleteUser(id);
        } catch(Exception e){
            throw new UbayException("El usuario está siendo usado en alguna parte de la web, no puedes borrarlo.");
        }

        request.getRequestDispatcher("delete.jsp").forward(request,response);
    }


}
