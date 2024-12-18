package dev.rohit.userservice.repositories;

import dev.rohit.userservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    void setIsDeleted(Long userId, boolean isDeleted);

    Optional<User> findByEmail(String email);
}
