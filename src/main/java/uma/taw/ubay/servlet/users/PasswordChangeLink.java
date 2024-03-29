package uma.taw.ubay.servlet.users;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uma.taw.ubay.dto.users.PasswordChangeDTO;
import uma.taw.ubay.service.users.UsersService;

import java.io.IOException;

/**
 * @author Servicio: Altair Bueno Calvente
 * @author Vista: José Luis Bueno Pachón
 */

@WebServlet("/users/passwordChangeLink")
public class PasswordChangeLink extends HttpServlet {
    @EJB
    UsersService usersService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        PasswordChangeDTO passwordChangeDTO = usersService.passwordChange(id);

        req.setAttribute("passwordChangeID", passwordChangeDTO.getPasswordChangeID());
        req.setAttribute("username", passwordChangeDTO.getUsername());
        req.getRequestDispatcher("passwordChange.jsp").forward(req, resp);
    }

}
