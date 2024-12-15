package dev.rohit.cartservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Cart extends BaseModel{

    private Long customerId;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CartItem> cartItems;
}
