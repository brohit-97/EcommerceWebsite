package dev.rohit.userservice.services;

import dev.rohit.userservice.dtos.UserDto;
import dev.rohit.userservice.models.User;

public interface UserService {

    User getUserById(Long userId);
    User updateUser(UserDto user);
    void deleteUser(Long userId);
    User createUser(String name, String email, String password);
}
