package dev.rohit.userservice.repositories;

import dev.rohit.userservice.models.Address;
import dev.rohit.userservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findAllByUser(User userId);
}
