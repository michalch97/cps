package exceptions;

public class NonDiscreteSignalException extends RuntimeException {
    public NonDiscreteSignalException() {
    }

    public NonDiscreteSignalException(String message) {
        super(message);
    }

    public NonDiscreteSignalException(String message, Throwable cause) {
        super(message, cause);
    }

    public NonDiscreteSignalException(Throwable cause) {
        super(cause);
    }
}
