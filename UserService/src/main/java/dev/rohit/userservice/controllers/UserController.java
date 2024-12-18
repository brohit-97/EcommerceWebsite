package dev.rohit.userservice.controllers;

import dev.rohit.userservice.dtos.UserDto;
import dev.rohit.userservice.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(UserDto.fromUser(userService.getUserById(userId)));
    }


    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(UserDto.fromUser(userService.createUser(userDto.getName(),userDto.getEmail(),userDto.getPassword())));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userRequestDto, @PathVariable Long userId) {
        return ResponseEntity.ok(UserDto.fromUser(userService.updateUser(userRequestDto)));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}
