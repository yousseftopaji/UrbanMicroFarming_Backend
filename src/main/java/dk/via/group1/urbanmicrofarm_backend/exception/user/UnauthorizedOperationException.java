package dk.via.group1.urbanmicrofarm_backend.exception.user;

public class UnauthorizedOperationException extends RuntimeException {
    public UnauthorizedOperationException(String message) {
        super(message);
    }
}
