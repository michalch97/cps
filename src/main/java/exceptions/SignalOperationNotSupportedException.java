package exceptions;

public class SignalOperationNotSupportedException extends RuntimeException {
    public SignalOperationNotSupportedException() {
    }

    public SignalOperationNotSupportedException(String message) {
        super(message);
    }

    public SignalOperationNotSupportedException(String message, Throwable cause) {
        super(message, cause);
    }

    public SignalOperationNotSupportedException(Throwable cause) {
        super(cause);
    }
}
