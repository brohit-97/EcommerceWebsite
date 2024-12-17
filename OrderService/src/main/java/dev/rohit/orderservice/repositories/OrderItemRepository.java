package dev.rohit.orderservice.repositories;

import dev.rohit.orderservice.models.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItems, Long> {
}
