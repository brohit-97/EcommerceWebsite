package dev.rohit.cartservice.services;


import dev.rohit.cartservice.models.Cart;

public interface CartService {
    public Cart getCartById(Long cartId);
    public Cart getCartByCustomer(Long customerId);
    public Cart addProductToCart(Long customerId, Long productId, int quantity);
    public boolean deleteCart(Long customerId);
}
