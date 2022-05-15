package uma.taw.ubay;

public class UbayException extends RuntimeException {
    public UbayException() {
        super();
    }

    public UbayException(String err) {
        super(err);
    }
}
