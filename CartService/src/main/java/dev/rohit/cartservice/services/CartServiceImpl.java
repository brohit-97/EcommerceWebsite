package dev.rohit.cartservice.services;

import dev.rohit.cartservice.exceptions.CartProcessingException;
import dev.rohit.cartservice.models.Cart;
import dev.rohit.cartservice.models.CartItem;
import dev.rohit.cartservice.repositories.CartRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }


    @Override
    public Cart getCartById(Long cartId) {
       Optional<Cart> cart = cartRepository.findById(cartId);
       if(cart.isPresent()) {
           return cart.get();
       }else {
           throw new CartProcessingException("Cart not found");
       }
    }

    @Override
    public Cart getCartByCustomer(Long customerId) {
        Optional<Cart> cart = cartRepository.findByCustomerId(customerId);
        if(cart.isPresent()) {
            return cart.get();
        }else {
            throw new CartProcessingException("Cart not found");
        }
    }

    @Override
    public Cart addProductToCart(Long customerId, Long productId, int quantity) {
        Optional<Cart> cartOptional = cartRepository.findByCustomerId(customerId);
        Cart cart;
        if(cartOptional.isPresent()) {
           cart = cartOptional.get();
        }else {
            cart = new Cart();
            cart.setCustomerId(customerId);
            cart.setCartItems(new ArrayList<>());
            cartRepository.save(cart);
        }

        Optional<CartItem> cartItemOptional = cart.getCartItems().stream().filter(cartItem -> cartItem.getProductId().equals(productId)).findFirst();
        if(cartItemOptional.isPresent()) {
            CartItem cartItem = cartItemOptional.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }else{
            CartItem cartItem = new CartItem();
            cartItem.setProductId(productId);
            cartItem.setQuantity(quantity);
            cartItem.setCart(cart);
            cart.getCartItems().add(cartItem);
        }
        return cartRepository.save(cart);
    }

    public Cart clearCart(Long cartId) {
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        if(cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            cart.getCartItems().clear();
            return cartRepository.save(cart);
        }else {
            throw new CartProcessingException("Cart not found for id" + cartId);
        }
    }

    @Override
    public boolean deleteCart(Long customerId) {
        Cart cart = getCartByCustomer(customerId);
        if(cart == null) {
            throw new CartProcessingException("Cart not found for customer" + customerId);
        }
        cartRepository.delete(cart);
        return true;
    }
}
