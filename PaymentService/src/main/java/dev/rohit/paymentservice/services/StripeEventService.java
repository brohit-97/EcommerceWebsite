package dev.rohit.paymentservice.services;

import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentLink;
import com.stripe.model.checkout.Session;
import com.stripe.net.RequestOptions;
import dev.rohit.paymentservice.auth.JWTClientUtils;
import dev.rohit.paymentservice.dtos.OrderDto;
import dev.rohit.paymentservice.dtos.PaymentResponseDto;
import dev.rohit.paymentservice.dtos.UserDto;
import dev.rohit.paymentservice.exceptions.StripeHandleEventException;
import dev.rohit.paymentservice.models.Payment;
import dev.rohit.paymentservice.models.PaymentStatus;
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

    @Value("${email.notification.topic}")
    String topic;

    public StripeEventService(OrderServiceClient orderServiceClient, UserServiceClient userServiceClient, EmailNotificationService emailNotificationService, PaymentService paymentService, JWTClientUtils jwtClientUtils) {
        this.orderServiceClient = orderServiceClient;
        this.userServiceClient = userServiceClient;
        this.emailNotificationService = emailNotificationService;
        this.paymentService = paymentService;
        this.jwtClientUtils = jwtClientUtils;
    }

    public void handlePaymentLinkCreatedEvent(Event event) {
        // logic to handle payment intent created event
        PaymentLink paymentLink = (PaymentLink) event.getDataObjectDeserializer()
                .getObject().orElse(null);
        if (paymentLink != null) {
            System.out.println("Created Payment link " + paymentLink.getId());
        }
    }

    public void handlePaymentLinkUpdatedEvent(Event event) {
        // logic to handle payment link updated event
        PaymentLink paymentLink = (PaymentLink) event.getDataObjectDeserializer()
                .getObject().orElse(null);
        if (paymentLink != null) {
            System.out.println("Updated payment link " + paymentLink.getId());
        }
    }

    public void handleCheckoutSessionCompletedEvent(Event event) throws StripeException {
        // logic to handle checkout session completed event
        Session session = (Session) event.getDataObjectDeserializer().getObject().orElse(null);
        if (session != null) {
            handlePaymentEvent(session);
        }
    }

    public void handlePaymentEvent(Session session) throws StripeException {
        // logic to handle stripe event
        String paymentLink = session.getPaymentLink();
        if(paymentLink == null) {
            throw new StripeHandleEventException("Payment link not found in stripe session");
        }
        Payment payment = paymentService.getPaymentByPaymentLink(paymentLink);
        if(payment == null) {
            throw new StripeHandleEventException("Payment not found for payment link " + paymentLink);
        }
        PaymentIntent paymentIntent = PaymentIntent.retrieve(session.getPaymentIntent(), RequestOptions.builder().setApiKey(stripeSecretKey).build());
        if(paymentIntent == null) {
            throw new StripeHandleEventException("Payment intent not found for payment link " + paymentLink);
        }
        payment.setPaymentStatus(PaymentStatus.fromString(paymentIntent.getStatus()));
        paymentService.savePayment(payment);
        orderServiceClient.updateOrderStatus(payment.getOrderId(), jwtClientUtils.getAccessToken(), payment.getPaymentStatus().name());
        OrderDto order = orderServiceClient.getOrder(payment.getOrderId(), jwtClientUtils.getAccessToken());
        if(order == null) {
            throw new StripeHandleEventException("Order not found for order id " + payment.getOrderId());
        }
        UserDto user = userServiceClient.getUser(order.getCustomerId());
        if(user == null) {
            throw new StripeHandleEventException("User not found for user id " + order.getCustomerId());
        }
        // send email notification
        sendEmailNotification(user, order, payment);
    }

    private void sendEmailNotification(UserDto user, OrderDto order, Payment payment) {
        String subject = "Payment "+payment.getPaymentStatus()+"  for - Order -" + order.getId();
        String body = "Here are you payment status details for  -" + order.getId() + " is " + payment.getPaymentStatus();
        emailNotificationService.sendEmail(user.getEmail(),subject,body,topic);
    }

}
