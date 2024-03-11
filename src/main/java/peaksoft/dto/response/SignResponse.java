package peaksoft.dto.response;

import lombok.Builder;
import peaksoft.enums.Role;

/**
 * @author Mukhammed Asantegin
 */
@Builder
public record SignResponse(
        String token,
        Long id,
        Role role,
        String email
){
}
