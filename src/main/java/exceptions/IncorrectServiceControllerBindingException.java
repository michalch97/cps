package exceptions;

public class IncorrectServiceControllerBindingException extends RuntimeException {
    public IncorrectServiceControllerBindingException() {
    }

    public IncorrectServiceControllerBindingException(String message) {
        super(message);
    }

    public IncorrectServiceControllerBindingException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectServiceControllerBindingException(Throwable cause) {
        super(cause);
    }
}
