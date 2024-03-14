package peaksoft.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.SignInRequest;
import peaksoft.dto.request.SignUpRequest;
import peaksoft.dto.response.RegisterResponse;
import peaksoft.dto.response.SignResponse;

import peaksoft.service.UserService;

/**
 * @author Mukhammed Asantegin
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Tag(name = "Auth API")
public class AuthAPI {
    private final UserService userService;

    @PostMapping
    @Operation(description = "This is registration")
    public RegisterResponse signUp(@RequestBody @Valid SignUpRequest signUpRequest){
        return userService.signUp(signUpRequest);
    }

    @GetMapping
    public SignResponse signIn(@RequestBody SignInRequest signInRequest){
        return userService.signIn(signInRequest);
    }


}
