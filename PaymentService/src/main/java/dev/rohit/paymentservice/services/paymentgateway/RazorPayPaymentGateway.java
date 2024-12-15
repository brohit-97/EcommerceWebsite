package dev.rohit.paymentservice.services.paymentgateway;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;
import dev.rohit.paymentservice.dtos.PaymentLinkResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.json.JSONObject;
import com.razorpay.RazorpayClient;

@Service
public class RazorPayPaymentGateway implements PaymentGateway {

    @Value("${service.order.url}")
    private String orderServiceUrl;

    private RazorpayClient razorpayClient;

    public RazorPayPaymentGateway(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }


    @Override
    public PaymentLinkResponseDto generatePaymentLink(Long orderId, Long amount) throws RazorpayException {
        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount",amount);
        paymentLinkRequest.put("currency","USD");
        paymentLinkRequest.put("accept_partial",false);
        paymentLinkRequest.put("expire_by",System.currentTimeMillis() + 8640000);
        paymentLinkRequest.put("reference_id",orderId);
        paymentLinkRequest.put("description","Payment for Order -"+orderId);
        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("notify",notify);
        paymentLinkRequest.put("reminder_enable",true);
        JSONObject notes = new JSONObject();
        notes.put("purchased",orderId);
        paymentLinkRequest.put("notes",notes);
        paymentLinkRequest.put("callback_url",orderServiceUrl + "/confirm?orderId=" + orderId);
        paymentLinkRequest.put("callback_method","get");
        PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);
        return new PaymentLinkResponseDto(payment.get("id"),payment.get("short_url"));
    }

}
