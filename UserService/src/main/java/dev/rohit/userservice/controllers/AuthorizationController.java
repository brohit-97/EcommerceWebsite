package dev.rohit.userservice.controllers;

import dev.rohit.userservice.dtos.*;
import dev.rohit.userservice.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthorizationController {
    private final AuthService authService;

    public AuthorizationController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserRegisterRequestDto userRegisterRequestDto) {
        return ResponseEntity.ok(UserDto.fromUser(authService.registerUser(userRegisterRequestDto)));
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(@RequestBody UserLoginRequestDto userLoginRequestDto) {
        return authService.loginUser(userLoginRequestDto.getEmail(), userLoginRequestDto.getPassword());
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody UserLogoutRequestDto userLogoutRequestDto) {
        authService.logoutUser(userLogoutRequestDto.getUserId(), userLogoutRequestDto.getToken());
        return ResponseEntity.ok(null);
    }

    @PostMapping("/validate")
    public ResponseEntity<ValidateUserTokenResponseDto> validate(@RequestBody ValidateUserTokenRequestDto validateUserTokenRequestDto) {
       return ResponseEntity.ok(authService.validateUserToken(validateUserTokenRequestDto.getUserId(), validateUserTokenRequestDto.getToken()));
    }
}
