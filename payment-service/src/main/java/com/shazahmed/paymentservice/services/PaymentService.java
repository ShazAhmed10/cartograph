package com.shazahmed.paymentservice.services;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private PaymentGatewaySelectionStrategy paymentGatewaySelectionStrategy;

    @Autowired
    public PaymentService(PaymentGatewaySelectionStrategy paymentGatewaySelectionStrategy) {
        this.paymentGatewaySelectionStrategy = paymentGatewaySelectionStrategy;
    }

    public String initiatePayment() throws RazorpayException, StripeException {
        return paymentGatewaySelectionStrategy
                .getPaymentGateway()
                .generateLink();
    }
}
