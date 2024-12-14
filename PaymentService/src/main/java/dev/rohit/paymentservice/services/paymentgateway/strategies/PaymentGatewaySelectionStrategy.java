package dev.rohit.paymentservice.services.paymentgateway.strategies;

import dev.rohit.paymentservice.services.paymentgateway.PaymentGateway;
import dev.rohit.paymentservice.services.paymentgateway.RazorPayPaymentGateway;
import dev.rohit.paymentservice.services.paymentgateway.StripePaymentGateway;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PaymentGatewaySelectionStrategy {
    private RazorPayPaymentGateway razorPayPaymentGateway;
    private StripePaymentGateway stripePaymentGateway;

    public PaymentGatewaySelectionStrategy(RazorPayPaymentGateway razorPayPaymentGateway, StripePaymentGateway stripePaymentGateway) {
        this.razorPayPaymentGateway = razorPayPaymentGateway;
        this.stripePaymentGateway = stripePaymentGateway;
    }

    public PaymentGateway getbestPaymentGateway() {
        // logic to select the best payment gateway
        int random = new Random().nextInt();
        if (random % 2 == 0) {
            return stripePaymentGateway;
        }
        return razorPayPaymentGateway;
    }
}
