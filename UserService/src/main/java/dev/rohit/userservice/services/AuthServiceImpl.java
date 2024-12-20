package dev.rohit.userservice.services;

import dev.rohit.userservice.auth.JWTUtil;
import dev.rohit.userservice.dtos.UserDto;
import dev.rohit.userservice.dtos.UserLoginResponseDto;
import dev.rohit.userservice.dtos.UserRegisterRequestDto;
import dev.rohit.userservice.dtos.AuthTokenResponseDto;
import dev.rohit.userservice.exceptions.InvalidPasswordException;
import dev.rohit.userservice.exceptions.NotFoundException;
import dev.rohit.userservice.exceptions.ResourceAlreayExistException;
import dev.rohit.userservice.models.Role;
import dev.rohit.userservice.models.Session;
import dev.rohit.userservice.models.SessionStatus;
import dev.rohit.userservice.models.User;
import dev.rohit.userservice.repositories.RoleRepository;
import dev.rohit.userservice.repositories.SessionRepository;
import dev.rohit.userservice.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMapAdapter;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private final RoleRepository roleRepository;
    private final JWTUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserService userService, UserRepository userRepository, SessionRepository sessionRepository,
                           RoleRepository roleRepository, JWTUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.roleRepository = roleRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    public User registerUser(UserRegisterRequestDto userRegisterRequestDto) {
        userRepository.findByEmail(userRegisterRequestDto.getEmail()).ifPresent(user -> {
            throw new ResourceAlreayExistException("User already exists");
        });
        User user = userService.createUser(userRegisterRequestDto.getName(), userRegisterRequestDto.getEmail(),
                passwordEncoder.encode(userRegisterRequestDto.getPassword()));
        return user;
    }

    @Override
    public ResponseEntity<UserLoginResponseDto> loginUser(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                String token = jwtUtil.generateToken(user.getId());
                Session session = new Session();
                session.setToken(token);
                session.setUser(user);
                session.setSessionStatus(SessionStatus.ACTIVE);
                session.setExpiry(jwtUtil.extractExpiration(token));
                sessionRepository.save(session);
                MultiValueMapAdapter<String,String> headers = new LinkedMultiValueMap<>();
                headers.add("AUTH_TOKEN",token);
                return new ResponseEntity<>(UserLoginResponseDto.fromUser(user),headers, HttpStatus.OK);
            }
        throw new InvalidPasswordException("Invalid password");
    }

    @Override
    public void logoutUser(Long userId, String token) {
        Optional<Session> sessionOptional = sessionRepository.findByUserIdAndToken(userId, token);
        if(sessionOptional.isPresent()) {
            Session session = sessionOptional.get();
            session.setSessionStatus(SessionStatus.INVALID);
            sessionRepository.save(session);
        }
    }

    @Override
    public User assignRoles(Long userId, Set<Long> roleIds) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        List<Role> roles =  roleRepository.findAllById(roleIds);
        if(roles.isEmpty()) {
            throw new NotFoundException("Roles not found");
        }
        Set<Role> currentRoles = user.getRoles();
        currentRoles.addAll(roles);
        return userRepository.save(user);
    }

    @Override
    public AuthTokenResponseDto validateUserToken(Long userId, String token) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        AuthTokenResponseDto authTokenResponseDto = new AuthTokenResponseDto();
        authTokenResponseDto.setUserDto(UserDto.fromUser(user));

        if(!jwtUtil.isTokenValid(token, userId)) {
            authTokenResponseDto.setSessionStatus(SessionStatus.INVALID);
            return authTokenResponseDto;
        }

        Optional<Session> sessionOptional = sessionRepository.findByUserIdAndToken(userId, token);
        if(sessionOptional.isPresent()) {
            Session session = sessionOptional.get();
            if(!session.getSessionStatus().equals(SessionStatus.ACTIVE)) {
                authTokenResponseDto.setSessionStatus(SessionStatus.EXPIRED);
            } else {
                authTokenResponseDto.setSessionStatus(SessionStatus.INVALID);
            }
            return authTokenResponseDto;
        }
        authTokenResponseDto.setSessionStatus(SessionStatus.ACTIVE);
        return authTokenResponseDto;
    }
}
