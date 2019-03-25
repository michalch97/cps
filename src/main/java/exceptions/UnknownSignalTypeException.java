package exceptions;

public class UnknownSignalTypeException extends RuntimeException {
    public UnknownSignalTypeException() {
    }

    public UnknownSignalTypeException(String message) {
        super(message);
    }

    public UnknownSignalTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownSignalTypeException(Throwable cause) {
        super(cause);
    }
}
