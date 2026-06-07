package com.rahim.store.api.controller.auth;
import com.rahim.store.api.model.LoginBody;
import com.rahim.store.api.model.LoginResponse;
import com.rahim.store.api.model.RegistrationBody;
import com.rahim.store.exception.UserAlreadyExistsException;
import com.rahim.store.model.LocalUser;
import com.rahim.store.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private UserService userservice;

    public AuthenticationController(UserService userservice) {
        this.userservice = userservice;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registeruser(@Valid @RequestBody RegistrationBody registrationbody) {
        try {
            userservice.registeruser(registrationbody);
            return ResponseEntity.ok().build();
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> LoginUser(@Valid @RequestBody LoginBody loginBody) {
        String jwt = userservice.LoginUser(loginBody);
        if (jwt == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } else {
            LoginResponse response = new LoginResponse();
            response.setJwt(jwt);
            return ResponseEntity.ok(response);
        }


    }

    @GetMapping("/me")
    public LocalUser GetLoggedInUserProfile(@AuthenticationPrincipal LocalUser user) {
        return user;
    }
}
