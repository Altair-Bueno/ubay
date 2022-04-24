package uma.taw.ubay.servlet.users;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uma.taw.ubay.dao.ClientFacade;
import uma.taw.ubay.dao.LoginCredentialsFacade;
import uma.taw.ubay.dao.PasswordResetFacade;
import uma.taw.ubay.entity.ClientEntity;
import uma.taw.ubay.entity.LoginCredentialsEntity;
import uma.taw.ubay.entity.PasswordResetEntity;

import java.io.IOException;
import java.util.Random;

@WebServlet("/users/passwordChangeLink")
public class PasswordChangeLink extends HttpServlet {
    @EJB
    PasswordResetFacade passwordResetFacade;
    @EJB
    LoginCredentialsFacade loginCredentialsFacade;
    @EJB
    ClientFacade clientFacade;

    private String generateRandomString(int size, Random random) {
        char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String passwordChangeID = generateRandomString(20,new Random());

        ClientEntity client = clientFacade.find(Integer.parseInt(id));
        LoginCredentialsEntity loginCredentialsEntity = loginCredentialsFacade.searchClientLoginByClient(client);

        PasswordResetEntity passwordResetEntity = new PasswordResetEntity();
        passwordResetEntity.setUser(loginCredentialsEntity);
        passwordResetEntity.setRequestId(passwordChangeID);
        passwordResetFacade.create(passwordResetEntity);

        req.setAttribute("passwordChangeID",passwordChangeID);
        req.setAttribute("username",loginCredentialsEntity.getUsername());
        req.getRequestDispatcher("passwordChange.jsp").forward(req,resp);
    }
}
