package dev.rohit.userservice.auth.repositories;

import java.util.Optional;

import dev.rohit.userservice.auth.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    Optional<Client> findByClientId(String clientId);
}
