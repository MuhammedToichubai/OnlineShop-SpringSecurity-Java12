package peaksoft.dto.response;

import lombok.Builder;

/**
 * @author Mukhammed Asantegin
 */
@Builder
public record RegisterResponse(String token, SimpleResponse simpleResponse) {

}
