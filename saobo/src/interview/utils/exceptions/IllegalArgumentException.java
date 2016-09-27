package interview.utils.exceptions;

public class IllegalArgumentException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public IllegalArgumentException() {
        super();
    }

    public IllegalArgumentException(String errorMessage) {
        super(errorMessage);
    }

    public IllegalArgumentException(String errorMessage, Exception cause) {
        super(errorMessage, cause);
    }
}
