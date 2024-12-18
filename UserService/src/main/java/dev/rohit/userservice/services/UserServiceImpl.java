package dev.rohit.userservice.services;

import dev.rohit.userservice.dtos.UserDto;
import dev.rohit.userservice.models.User;
import dev.rohit.userservice.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User getUserById(Long userId) {
       return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User updateUser(UserDto user) {
        User oldUser = userRepository.findById(user.getId()).orElseThrow(() -> new RuntimeException("User not found"));
        if (isNotNullOrEmpty(user.getName())) {
            oldUser.setName(user.getName());
        }
        if (isNotNullOrEmpty(user.getEmail())) {
            oldUser.setEmail(user.getEmail());
        }
        if (isNotNullOrEmpty(user.getProfilePic())) {
            oldUser.setProfilePic(user.getProfilePic());
        }
        return userRepository.save(oldUser);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.setIsDeleted(userId, true);
    }

    @Override
    public User createUser(String name, String email, String password) {
        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setDeleted(false);
        newUser.setActive(true);
        newUser.setPassword(password);
        return userRepository.save(newUser);
    }

    private boolean isNotNullOrEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
