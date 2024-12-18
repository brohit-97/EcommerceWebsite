package dev.rohit.userservice.auth.services;

import dev.rohit.userservice.auth.models.UserDetailsPOJO;
import dev.rohit.userservice.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByEmail(username)
                .map(user -> new UserDetailsPOJO())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
