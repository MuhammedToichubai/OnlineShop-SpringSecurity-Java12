package peaksoft.exceptions;

/**
 * @author Mukhammed Asantegin
 */
public class NotFoundException extends RuntimeException{
    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }
}
