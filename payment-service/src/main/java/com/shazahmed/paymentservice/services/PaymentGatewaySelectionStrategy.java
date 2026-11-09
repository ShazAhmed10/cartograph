package com.shazahmed.paymentservice.services;

import com.shazahmed.paymentservice.thirdpartyclients.paymentgateways.PaymentGateway;
import com.shazahmed.paymentservice.thirdpartyclients.paymentgateways.RazorpayPaymentGatewayClient;
import com.shazahmed.paymentservice.thirdpartyclients.paymentgateways.StripePaymentGatewayClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentGatewaySelectionStrategy {
    private RazorpayPaymentGatewayClient razorpay;
    private StripePaymentGatewayClient stripe;

    @Autowired
    public PaymentGatewaySelectionStrategy(RazorpayPaymentGatewayClient razorpay,
                                           StripePaymentGatewayClient stripe) {
        this.razorpay = razorpay;
        this.stripe = stripe;
    }

    public PaymentGateway getPaymentGateway() {
        //selection logic to be written here

        return razorpay;
    }
}
