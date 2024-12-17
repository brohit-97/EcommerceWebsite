package dev.rohit.orderservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class OrderItems extends BaseModel {

    private Long productId;
    private Integer quantity;
    private Double price;
    @ManyToOne
    private Order order;

    public OrderItems() {
    }

    public OrderItems(Long productId, Integer quantity, Double price, Order orderId) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.order = orderId;
    }


}
