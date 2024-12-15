package dev.rohit.paymentservice.services;

import dev.rohit.paymentservice.auth.JWTUtils;
import dev.rohit.paymentservice.dtos.InitiatePaymentRequestDto;
import dev.rohit.paymentservice.dtos.OrderDto;
import dev.rohit.paymentservice.dtos.PaymentLinkResponseDto;
import dev.rohit.paymentservice.exceptions.ServiceCallFailed;
import dev.rohit.paymentservice.models.Payment;
import dev.rohit.paymentservice.models.PaymentStatus;
import dev.rohit.paymentservice.respositories.PaymentRepository;
import dev.rohit.paymentservice.services.clients.OrderServiceClient;
import dev.rohit.paymentservice.services.paymentgateway.PaymentGateway;
import dev.rohit.paymentservice.services.paymentgateway.strategies.PaymentGatewaySelectionStrategy;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

     private PaymentRepository paymentRepository;
     private OrderServiceClient orderServiceClient;

     private PaymentGatewaySelectionStrategy paymentGatewaySelectionStrategy;

    public PaymentService(PaymentRepository paymentRepository, OrderServiceClient orderServiceClient, PaymentGatewaySelectionStrategy paymentGatewaySelectionStrategy) {
        this.paymentRepository = paymentRepository;
        this.orderServiceClient = orderServiceClient;
        this.paymentGatewaySelectionStrategy = paymentGatewaySelectionStrategy;
    }

    public PaymentLinkResponseDto initiatePayment(Authentication authentication, InitiatePaymentRequestDto initiatePaymentRequestDto) throws Exception {
        // initiate payment
        PaymentGateway paymentGateway = paymentGatewaySelectionStrategy.getbestPaymentGateway();
        String token = JWTUtils.extractToken(authentication);
        OrderDto order = orderServiceClient.getOrder(initiatePaymentRequestDto.getOrderId(), token);
        if(order == null) {
            throw new ServiceCallFailed("Order service API order not found " + initiatePaymentRequestDto.getOrderId());
        }
        Long amount = Math.round(order.getAmount() * 100);
        PaymentLinkResponseDto paymentLinkResponseDto = paymentGateway.generatePaymentLink(order.getId(), amount);
        Payment payment = buildPaymentObject(order,paymentLinkResponseDto);
        orderServiceClient.updateOrderWithPayment(order.getId(), token, payment.getId());
        return paymentLinkResponseDto;
    }

    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public List<Payment> getPayments() {
        return paymentRepository.findAll();
    }

    public Optional<Payment> getPayment(Long id) {
        return paymentRepository.findById(id);
    }

    public Payment getByOrderId(Long orderId) {
        return paymentRepository.findByOrderId(orderId);
    }

    public Payment getByPaymentId(String paymentId) {
        return paymentRepository.findByPaymentId(paymentId);
    }

    public Payment getByPaymentLink(String paymentLink) {
        return paymentRepository.findByPaymentLink(paymentLink);
    }

    private Payment buildPaymentObject(OrderDto order, PaymentLinkResponseDto paymentLinkResponseDto) {
        Payment payment = new Payment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getAmount());
        payment.setPaymentStatus(PaymentStatus.INITIATED);
        payment.setPaymentLink(paymentLinkResponseDto.getUrl());
        payment.setPaymentId(paymentLinkResponseDto.getId());
        paymentRepository.save(payment);
        return payment;
    }

}
