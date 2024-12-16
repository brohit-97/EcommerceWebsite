package dev.rohit.orderservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Order getOrderId() {
        return order;
    }

    public void setOrderId(Order orderId) {
        this.order = orderId;
    }

}
