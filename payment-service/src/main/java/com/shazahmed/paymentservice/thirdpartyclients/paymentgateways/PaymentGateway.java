package com.shazahmed.paymentservice.thirdpartyclients.paymentgateways;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface PaymentGateway {
    public String generateLink() throws RazorpayException, StripeException;
}
