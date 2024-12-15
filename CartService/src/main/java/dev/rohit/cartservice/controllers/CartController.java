package dev.rohit.cartservice.controllers;

import dev.rohit.cartservice.auth.JWTUtils;
import dev.rohit.cartservice.dtos.AddProductToCartRequestDto;
import dev.rohit.cartservice.dtos.CartResponseDto;
import dev.rohit.cartservice.exceptions.CartProcessingException;
import dev.rohit.cartservice.models.Cart;
import dev.rohit.cartservice.services.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public Cart getCartById(Long cartId) {
        return cartService.getCartById(cartId);
    }

    @GetMapping("/customer")
    public ResponseEntity<CartResponseDto> getCartByCustomer(Authentication authentication) {
        Long customerId = JWTUtils.extractUserId(authentication);
        Cart cart = cartService.getCartByCustomer(customerId);
        return ResponseEntity.ok(CartResponseDto.from(cart));
    }

    @PostMapping
    public ResponseEntity<CartResponseDto> addProductToCart(Authentication authentication,
                                                            AddProductToCartRequestDto addProductToCartRequestDto) {
        Long customerId = JWTUtils.extractUserId(authentication);
        Cart cart = cartService.addProductToCart(customerId,
                addProductToCartRequestDto.getProductId(), addProductToCartRequestDto.getQuantity());
        return ResponseEntity.ok(CartResponseDto.from(cart));
    }

    @DeleteMapping
    public ResponseEntity<Boolean> clearCart(Authentication authentication) {
        Long customerId = JWTUtils.extractUserId(authentication);
        return ResponseEntity.ok(cartService.deleteCart(customerId));
    }
}
