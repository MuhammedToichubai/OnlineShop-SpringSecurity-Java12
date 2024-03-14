package peaksoft.exceptions.response;

import lombok.Builder;
import org.springframework.http.HttpStatus;

/**
 * @author Mukhammed Asantegin
 */
@Builder
public record ExceptionResponse (
        HttpStatus httpStatus,
        String exceptionClassName,
        String message
)
{}
