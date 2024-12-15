package dev.rohit.paymentservice.services;

import dev.rohit.paymentservice.auth.JWTUtils;
import dev.rohit.paymentservice.dtos.InitiatePaymentRequestDto;
import dev.rohit.paymentservice.dtos.OrderDto;
import dev.rohit.paymentservice.dtos.PaymentLinkResponseDto;
import dev.rohit.paymentservice.dtos.PaymentResponseDto;
import dev.rohit.paymentservice.exceptions.NotFoundException;
import dev.rohit.paymentservice.exceptions.ServiceCallFailed;
import dev.rohit.paymentservice.models.Payment;
import dev.rohit.paymentservice.models.PaymentStatus;
import dev.rohit.paymentservice.respositories.PaymentRepository;
import dev.rohit.paymentservice.services.clients.OrderServiceClient;
import dev.rohit.paymentservice.services.paymentgateway.PaymentGateway;
import dev.rohit.paymentservice.services.paymentgateway.strategies.PaymentGatewaySelectionStrategy;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<PaymentResponseDto> getAllPayments() {
        List<Payment> payments =  paymentRepository.findAll();
        List<PaymentResponseDto> paymentResponseDtos = new ArrayList<>();
        // Convert list of payments to list of PaymentResponseDto
        payments.forEach(payment -> {
            paymentResponseDtos.add(PaymentResponseDto.buildFromPayment(payment));
        });
        return paymentResponseDtos;
    }

    public PaymentResponseDto getPayment(Long id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        if(payment.isPresent()) {
            return PaymentResponseDto.buildFromPayment(payment.get());
        }else{
            throw new NotFoundException("Payment not found with id " + id);
        }
    }

    public PaymentResponseDto getByOrderId(Long orderId) {
        Optional<Payment> payment = paymentRepository.findByOrderId(orderId);
        if (payment.isPresent()) {
            return PaymentResponseDto.buildFromPayment(payment.get());
        } else {
            throw new NotFoundException("Payment not found with order id " + orderId);
        }

    }

    public PaymentResponseDto getByPaymentId(String paymentId) {
        Optional<Payment> payment = paymentRepository.findByPaymentId(paymentId);
        if (payment.isPresent()) {
            return PaymentResponseDto.buildFromPayment(payment.get());
        } else {
            throw new NotFoundException("Payment not found with payment Id " + paymentId);
        }
    }

    public PaymentResponseDto getByPaymentLink(String paymentLink) {
        Optional<Payment> payment = paymentRepository.findByPaymentLink(paymentLink);
        if (payment.isPresent()) {
            return PaymentResponseDto.buildFromPayment(payment.get());
        } else {
            throw new NotFoundException("Payment not found with payment link " + paymentLink);
        }
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

    public Payment getPaymentByPaymentLink(String paymentLink) {
        Optional<Payment> payment = paymentRepository.findByPaymentLink(paymentLink);
        if(payment.isPresent()) {
            return payment.get();
        }else{
            throw new NotFoundException("Payment not found with payment link " + paymentLink);
        }
    }

}
