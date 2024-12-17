package dev.rohit.orderservice.services;

import dev.rohit.orderservice.auth.JWTUtils;
import dev.rohit.orderservice.dtos.CartDto;
import dev.rohit.orderservice.dtos.CartItemDto;
import dev.rohit.orderservice.dtos.ProductDto;
import dev.rohit.orderservice.exceptions.NotFoundException;
import dev.rohit.orderservice.models.Order;
import dev.rohit.orderservice.models.OrderItems;
import dev.rohit.orderservice.models.OrderStatus;
import dev.rohit.orderservice.repositories.OrderItemRepository;
import dev.rohit.orderservice.repositories.OrderRepository;
import dev.rohit.orderservice.services.clients.CartServiceClient;
import dev.rohit.orderservice.services.clients.ProductServiceClient;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartServiceClient cartServiceClient;
    private final ProductServiceClient productServiceClient;

    public OrderServiceImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository, CartServiceClient cartServiceClient, ProductServiceClient productServiceClient) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.cartServiceClient = cartServiceClient;
        this.productServiceClient = productServiceClient;
    }

    @Override
    public Order createOrder(Authentication authentication) {
        String token = JWTUtils.extractToken(authentication);
        Long customerId = JWTUtils.extractUserId(authentication);

        CartDto cartDto = cartServiceClient.getCartForCustomer(token);
        if(cartDto == null || cartDto.getCartItems().isEmpty()) {
            throw new NotFoundException("Cart is empty");
        }
        Order order = new Order();
        order.setCustomerId(customerId);
        order.setStatus(OrderStatus.PENDING);
        Order savedOrder = orderRepository.save(order);
        Double total = 0.0;
        for(CartItemDto cartItemDto: cartDto.getCartItems()) {
            ProductDto productDto = productServiceClient.getProductById(cartItemDto.getProductId());
            if(productDto == null) {
                throw new NotFoundException("Product not found");
            }
            if(productDto.getQuantity() < cartItemDto.getQuantity()) {
                throw new NotFoundException("Product quantity not available");
            }
            OrderItems orderItems = new OrderItems();
            orderItems.setProductId(productDto.getId());
            orderItems.setQuantity(cartItemDto.getQuantity());
            orderItems.setPrice(productDto.getPrice());
            orderItems.setOrderId(order);
            productDto.setQuantity(productDto.getQuantity() - orderItems.getQuantity());
            productServiceClient.updateProduct(productDto);
            total += productDto.getPrice() * cartItemDto.getQuantity();
            orderItemRepository.save(orderItems);
        }
        order.setOrderTotal(total);
        orderRepository.save(order);
        cartServiceClient.deleteCartForCustomer(token);
        return savedOrder;
    }

    @Override
    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new NotFoundException("Order not found"));
    }


    @Override
    public Order cancelOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new NotFoundException("Order not found"));
        order.setStatus(OrderStatus.CANCELLED);
        for(OrderItems orderItems: order.getOrderItems()) {
            ProductDto productDto = productServiceClient.getProductById(orderItems.getProductId());
            productDto.setQuantity(productDto.getQuantity() + orderItems.getQuantity());
            productServiceClient.updateProduct(productDto);
        }
        return orderRepository.save(order);
    }

    @Override
    public Order completeOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new NotFoundException("Order not found"));
        order.setStatus(OrderStatus.DELIVERED);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrderForCustomer(Authentication authentication) {
        Long customerId = JWTUtils.extractUserId(authentication);
        return orderRepository.findAllByCustomerId(customerId);
    }
}
