package dev.rohit.cartservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class CartItem extends BaseModel{

    private Long productId;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

}
