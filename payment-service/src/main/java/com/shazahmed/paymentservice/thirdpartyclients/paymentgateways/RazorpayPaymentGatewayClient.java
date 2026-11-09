package com.shazahmed.paymentservice.thirdpartyclients.paymentgateways;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class RazorpayPaymentGatewayClient implements PaymentGateway{
    private String key;
    private String secret;

    public RazorpayPaymentGatewayClient(@Value("${RAZORPAY_KEY}")String key,
                                        @Value("${RAZORPAY_SECRET}") String secret) {
        this.key = key;
        this.secret = secret;
    }

    @Override
    public String generateLink() throws RazorpayException{
        PaymentLink payment;
        RazorpayClient razorpay = new RazorpayClient(key, secret);

        JSONObject paymentLinkRequest = new JSONObject();
        long expireBy = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(5);
        paymentLinkRequest.put("amount",1000);
        paymentLinkRequest.put("currency","INR");
        paymentLinkRequest.put("accept_partial",false);
        paymentLinkRequest.put("first_min_partial_amount",100);
        paymentLinkRequest.put("expire_by",expireBy);
        paymentLinkRequest.put("reference_id", UUID.randomUUID());
        paymentLinkRequest.put("description","Payment for policy no #00001");

        JSONObject customer = new JSONObject();
        customer.put("name","Sample Customer");
        customer.put("contact","+910000000000");
        customer.put("email","customer@example.com");

        paymentLinkRequest.put("customer",customer);

        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);

        paymentLinkRequest.put("notify",notify);
        paymentLinkRequest.put("reminder_enable",false);
        paymentLinkRequest.put("callback_url","https://google.com");
        paymentLinkRequest.put("callback_method","get");

        payment = razorpay.paymentLink.create(paymentLinkRequest);

        return payment.toString();
    }
}
