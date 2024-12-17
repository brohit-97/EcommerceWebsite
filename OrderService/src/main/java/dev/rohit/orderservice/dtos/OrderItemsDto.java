package dev.rohit.orderservice.dtos;

import dev.rohit.orderservice.models.OrderItems;

public class OrderItemsDto {

    private Long productId;
    private Integer quantity;
    private Double price;

    public OrderItemsDto() {
    }

    public OrderItemsDto(Long productId, Integer quantity, Double price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public static OrderItemsDto from(OrderItems orderItems) {
        OrderItemsDto orderItemsDto = new OrderItemsDto();
        orderItemsDto.setProductId(orderItems.getProductId());
        orderItemsDto.setQuantity(orderItems.getQuantity());
        orderItemsDto.setPrice(orderItems.getPrice());
        return orderItemsDto;
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
}
