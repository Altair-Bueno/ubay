package uma.taw.ubay.auth;

public class Auth {
    // From https://stackoverflow.com/a/1223146
    public final static String USERNAME_REGEX = "^[a-zA-Z0-9]+([a-zA-Z0-9](_|-| )[a-zA-Z0-9])*[a-zA-Z0-9]+$";
    public final static String PASSWORD_REGEX = ".*";

    public final static String USERNAME_PARAMETER = "username";
    public final static String PASSWORD_PARAMETER = "password";

    public final static String ERROR_MESSAGE = "Bad username or password";


    public final static String INDEX_REDIRECT = "../";
    public final static String LOGIN_REDIRECT = "login.jsp";
}
