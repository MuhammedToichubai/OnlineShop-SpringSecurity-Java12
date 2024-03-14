package peaksoft.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import peaksoft.validation.PhoneNumberValidation;

/**
 * @author Mukhammed Asantegin
 */
@Getter @Setter
public class SignUpRequest {
    @NotBlank(message = "Name is blank")
    private String name;
    @Email()
    private String email;
    private String password;
    private String passwordConfirm;
    @PhoneNumberValidation
    private String phoneNumber;

}
