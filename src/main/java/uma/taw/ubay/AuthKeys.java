package uma.taw.ubay;

/**
 * Multiple constant values shared between auth related servlets
 */
public class AuthKeys {
    // From https://stackoverflow.com/a/1223146
    public final static String USERNAME_REGEX = "^[a-zA-Z0-9]+([a-zA-Z0-9](_|-| )[a-zA-Z0-9])*[a-zA-Z0-9]+$";
    public final static String PASSWORD_REGEX = ".{8,}";

    public final static String ERROR_MESSAGE = "Bad username or password";

    public final static String INDEX_REDIRECT = "../";
    public final static String LOGIN_REDIRECT = "login.jsp";
    public final static String REGISTER_REDIRECT = "register.jsp";

    public final static String USERNAME_PARAMETER = "username";
    public final static String PASSWORD_PARAMETER = "password";
    public static final String NAME_PARAMETER = "name";
    public static final String LAST_NAME_PARAMETER = "lastName";
    public static final String ADDRESS_PARAMETER = "address";
    public static final String CITY_PARAMETER = "city";
    public static final String BIRTH_PARAMETER = "birth";
    public static final String GENDER_PARAMETER = "gender";
}
