package dev.rohit.cartservice.dtos;

import dev.rohit.cartservice.models.CartItem;

public class CartItemResponseDto {
    private Long id;
    private Long productId;
    private Integer quantity;

    public CartItemResponseDto() {
    }

    public CartItemResponseDto(Long id, Long productId, Integer quantity) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public static CartItemResponseDto from(CartItem cartItem) {
        return new CartItemResponseDto(cartItem.getId(), cartItem.getProductId(), cartItem.getQuantity());
    }

}
