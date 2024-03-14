package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.ProductInnerPageResponse;
import peaksoft.dto.request.SignInRequest;
import peaksoft.dto.request.SignUpRequest;
import peaksoft.dto.response.SignResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.model.User;
import peaksoft.service.UserService;

import java.security.Principal;

/**
 * @author Mukhammed Asantegin
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserAPI {
    private final UserService userService;

    @PutMapping("/{userID}")
    @Secured({"ADMIN", "USER"})
    public SimpleResponse updateUser(@PathVariable Long userID, @RequestBody User user, Principal principal){
        return userService.update(principal, userID, user);
    }


    @GetMapping("/{userId}")
    public User findById(@PathVariable Long userId){
        return userService.findById(userId);
    }

}
