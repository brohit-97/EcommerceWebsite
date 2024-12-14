package dev.rohit.paymentservice.services.paymentgateway;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;
import dev.rohit.paymentservice.dtos.PaymentLinkResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StripePaymentGateway implements PaymentGateway {

    @Value("${stripe.secret.key}")
    private String stripeApiSecretKey;

    @Value("${service.order.url}")
    private String orderServiceUrl;


    @Override
    public PaymentLinkResponseDto generatePaymentLink(Long orderId, Long amount) throws StripeException {
        Stripe.apiKey = stripeApiSecretKey;

        // logic to generate payment link
        ProductCreateParams productCreateParams = ProductCreateParams.builder()
                .setName("Order -" + orderId)
                .setActive(true)
                .build();

        // create product
        Product product = Product.create(productCreateParams);

        // create price
        PriceCreateParams priceCreateParams = PriceCreateParams.builder()
                .setCurrency("usd")
                .setProduct(product.getId())
                .setUnitAmount(amount)
                .build();
        Price price = Price.create(priceCreateParams);

        PaymentLinkCreateParams paymentLinkCreateParams = PaymentLinkCreateParams.builder()
                .addLineItem(
                        PaymentLinkCreateParams.LineItem.builder()
                                .setPrice(price.getId())
                                .setQuantity(1L)
                                .build()
                ).setAfterCompletion(PaymentLinkCreateParams.AfterCompletion.builder()
                        .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                        .setRedirect(PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                .setUrl(orderServiceUrl + "/confirm?orderId=" + orderId).build())
                        .build()).build();

        PaymentLink paymentLink = PaymentLink.create(paymentLinkCreateParams);
        return new PaymentLinkResponseDto(paymentLink.getUrl(), paymentLink.getId());
    }
}
