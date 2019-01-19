package tesis.app.common.exception;

public class ResourceIdMismatchException extends RuntimeException {

    public ResourceIdMismatchException() {
    }

    public ResourceIdMismatchException(String message) {
        super(message);
    }

    public ResourceIdMismatchException(Throwable cause) {
        super(cause);
    }
    
    public ResourceIdMismatchException(String message, Throwable cause) {
        super(message, cause);
    }

}
