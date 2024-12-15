package dev.rohit.paymentservice.services;

import com.stripe.model.checkout.Session;
import dev.rohit.paymentservice.auth.JWTClientUtils;
import dev.rohit.paymentservice.exceptions.StripeHandleEventException;
import dev.rohit.paymentservice.models.Payment;
import dev.rohit.paymentservice.notification.EmailNotificationService;
import dev.rohit.paymentservice.services.clients.OrderServiceClient;
import dev.rohit.paymentservice.services.clients.UserServiceClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StripeEventService {

    private OrderServiceClient orderServiceClient;
    private UserServiceClient userServiceClient;
    private EmailNotificationService emailNotificationService;
    private PaymentService paymentService;
    private JWTClientUtils jwtClientUtils;

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    public StripeEventService(OrderServiceClient orderServiceClient, UserServiceClient userServiceClient, EmailNotificationService emailNotificationService, PaymentService paymentService, JWTClientUtils jwtClientUtils) {
        this.orderServiceClient = orderServiceClient;
        this.userServiceClient = userServiceClient;
        this.emailNotificationService = emailNotificationService;
        this.paymentService = paymentService;
        this.jwtClientUtils = jwtClientUtils;
    }

    public void handlePaymentEvent(Session session) {
        // logic to handle stripe event
        String paymentLink = session.getPaymentLink();
        if(paymentLink == null) {
            throw new StripeHandleEventException("Payment link not found in stripe session");
        }
        Payment payment = paymentService.get(paymentLink);
    }


}
