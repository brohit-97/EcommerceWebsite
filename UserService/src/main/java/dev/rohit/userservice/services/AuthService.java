package dev.rohit.userservice.services;

import dev.rohit.userservice.dtos.UserLoginResponseDto;
import dev.rohit.userservice.dtos.UserRegisterRequestDto;
import dev.rohit.userservice.dtos.AuthTokenResponseDto;
import dev.rohit.userservice.models.User;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface AuthService {

    User registerUser(UserRegisterRequestDto userRegisterRequestDto);

    ResponseEntity<UserLoginResponseDto> loginUser(String email, String password);

    void logoutUser(Long userId, String token);

    User assignRoles(Long userId, Set<Long> roleIds);

    AuthTokenResponseDto validateUserToken(Long userId, String token);
}
