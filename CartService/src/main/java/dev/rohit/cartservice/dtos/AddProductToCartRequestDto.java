package dev.rohit.cartservice.dtos;


import lombok.Data;

@Data
public class AddProductToCartRequestDto {
    private Long productId;
    private Integer quantity;
}
