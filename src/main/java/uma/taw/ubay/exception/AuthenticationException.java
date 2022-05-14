package uma.taw.ubay.exception;

import jakarta.servlet.ServletException;
/**
 * @author Altair Bueno
 */

public class AuthenticationException extends ServletException {
    public AuthenticationException() {
    }

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Throwable rootCause) {
        super(message, rootCause);
    }

    public AuthenticationException(Throwable rootCause) {
        super(rootCause);
    }
}
