package dev.rohit.userservice.repositories;

import dev.rohit.userservice.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
        Optional<Session> findByUserIdAndToken(Long userId, String token);
}
