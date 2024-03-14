package peaksoft.exceptions;

/**
 * @author Mukhammed Asantegin
 */
public class ForbiddenException extends RuntimeException{
    public ForbiddenException() {
    }

    public ForbiddenException(String message) {
        super(message);
    }
}
