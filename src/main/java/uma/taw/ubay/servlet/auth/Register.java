package uma.taw.ubay.servlet.auth;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uma.taw.ubay.AuthKeys;
import uma.taw.ubay.dao.ClientFacade;
import uma.taw.ubay.dao.LoginCredentialsFacade;
import uma.taw.ubay.entity.ClientEntity;
import uma.taw.ubay.entity.GenderEnum;
import uma.taw.ubay.entity.KindEnum;
import uma.taw.ubay.entity.LoginCredentialsEntity;

import java.io.IOException;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Servlet in charge of creating a new user session with the received username
 * and password
 */
@WebServlet("/auth/register")
public class Register extends HttpServlet {
    @EJB
    LoginCredentialsFacade loginCredentialsFacade;
    @EJB
    ClientFacade clientFacade;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter(AuthKeys.USERNAME_PARAMETER);
        String password = req.getParameter(AuthKeys.PASSWORD_PARAMETER);

        String name = req.getParameter(AuthKeys.NAME_PARAMETER);
        String lastName = req.getParameter(AuthKeys.LAST_NAME_PARAMETER);
        String address = req.getParameter(AuthKeys.ADDRESS_PARAMETER);
        String city = req.getParameter(AuthKeys.CITY_PARAMETER);
        String genderParameter = req.getParameter(AuthKeys.GENDER_PARAMETER);
        String birthDateParameter = req.getParameter(AuthKeys.BIRTH_PARAMETER);

        try {
            boolean anyNull = Stream.of(
                            username, password,
                            name, lastName,
                            address, city,
                            birthDateParameter, genderParameter
                    ).anyMatch(Objects::isNull);
            if (anyNull)
                throw new IllegalArgumentException("All fields are required");
            if (!username.matches(AuthKeys.USERNAME_REGEX))
                throw new IllegalArgumentException("Username invalid format");
            if (!password.matches(AuthKeys.PASSWORD_REGEX))
                throw new IllegalArgumentException("Password invalid format");

            java.sql.Date birthDate = java.sql.Date.valueOf(birthDateParameter);
            GenderEnum gender = GenderEnum.valueOf(genderParameter);

            ClientEntity client = new ClientEntity(name, lastName, address, city, birthDate, gender);
            LoginCredentialsEntity login = new LoginCredentialsEntity(username, password, KindEnum.client, client);

            clientFacade.create(client);
            loginCredentialsFacade.create(login);

            resp.sendRedirect(AuthKeys.LOGIN_REDIRECT);
        } catch (IllegalArgumentException e) {
            resp.sendError(400, e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(AuthKeys.REGISTER_REDIRECT).forward(req, resp);
    }
}
