package dev.rohit.paymentservice.webhooks;

import com.stripe.model.Event;
import com.stripe.net.Webhook;
import dev.rohit.paymentservice.dtos.StripeException;
import dev.rohit.paymentservice.services.StripeEventService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/webhooks/stripe")
public class StripeWebhookController {

    @Value("${stripe.webhook.secret}")
    private String stripeWebhookSecret;
    private StripeEventService stripeEventService;

    public StripeWebhookController(StripeEventService stripeEventService) {
        this.stripeEventService = stripeEventService;
    }

    @PostMapping
    public void handleStripeWebhook(HttpServletRequest httpServletRequest) throws Exception {
        String payload = httpServletRequest.getReader().lines().collect(Collectors.joining("\n"));
        Event event;
        try {
            String sigHeader = httpServletRequest.getHeader("Stripe-Signature");
            event = Webhook.constructEvent(payload, sigHeader, stripeWebhookSecret);
        } catch (Exception e) {
            throw new StripeException("Failed to validate stripe event signature");
        }

        switch (event.getType()){
            case "checkout.session.completed":
                stripeEventService.handleCheckoutSessionCompletedEvent(event);
                break;
            case "payment_link.created":
                stripeEventService.handlePaymentLinkCreatedEvent(event);
                break;
            case "payment_link.updated":
                stripeEventService.handlePaymentLinkUpdatedEvent(event);
                break;
            default:
                System.out.println("Unhandled event type: " + event.getType());
                break;
        }
    }


}
