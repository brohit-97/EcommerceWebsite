package dev.rohit.cartservice.dtos;

import dev.rohit.cartservice.models.Cart;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CartResponseDto {
    private Long cartId;
    private Long customerId;
    private List<CartItemResponseDto> cartItems;

    public static CartResponseDto from(Cart cart) {
        CartResponseDto cartResponseDto = new CartResponseDto();
        List<CartItemResponseDto> cartItemResponseDtos = new ArrayList<>();
        cartResponseDto.setCartId(cart.getId());
        cartResponseDto.setCustomerId(cart.getCustomerId());
        cart.getCartItems().forEach(cartItem -> cartItemResponseDtos.add(CartItemResponseDto.from(cartItem)));
        cartResponseDto.setCartItems(cartItemResponseDtos);
        return cartResponseDto;
    }
}
