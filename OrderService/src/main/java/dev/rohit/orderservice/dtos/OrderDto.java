package dev.rohit.orderservice.dtos;

import dev.rohit.orderservice.models.Order;
import dev.rohit.orderservice.models.OrderItems;

import java.util.ArrayList;
import java.util.List;

public class OrderDto {

    private Long orderId;
    private Long customerId;
    private List<OrderItemsDto> items;
    private String status;
    private Double orderTotal;
    private Long paymentId;
    private String shippingAddress;

    public OrderDto() {
    }

    public OrderDto(Long orderId, Long customerId, List<OrderItemsDto> items, String status, Double orderTotal, Long paymentId, String shippingAddress) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.items = items;
        this.status = status;
        this.orderTotal = orderTotal;
        this.paymentId = paymentId;
        this.shippingAddress = shippingAddress;
    }

    public static OrderDto from(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(order.getId());
        orderDto.setCustomerId(order.getCustomerId());
        orderDto.setOrderTotal(order.getOrderTotal());
        orderDto.setStatus(order.getStatus().name());
        orderDto.setPaymentId(order.getPaymentId());
        orderDto.setShippingAddress(order.getShippingAddress());
        List<OrderItems> orderItems = order.getOrderItems();
        List<OrderItemsDto> orderItemsDtos = new ArrayList<>();
        for (OrderItems orderItem : orderItems) {
            orderItemsDtos.add(OrderItemsDto.from(orderItem));
        }
        orderDto.setItems(orderItemsDtos);
        return orderDto;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderItemsDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemsDto> items) {
        this.items = items;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
