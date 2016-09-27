package interview.utils.exceptions;

public class OverlapIntervalsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public OverlapIntervalsException() {
        super();
    }

    public OverlapIntervalsException(String errorMessage) {
        super(errorMessage);
    }

    public OverlapIntervalsException(String errorMessage, Exception cause) {
        super(errorMessage, cause);
    }
}
