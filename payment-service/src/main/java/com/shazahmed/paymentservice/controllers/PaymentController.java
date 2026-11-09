package com.shazahmed.paymentservice.controllers;

import com.razorpay.RazorpayException;
import com.shazahmed.paymentservice.services.PaymentService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<String> initiatePayment() throws RazorpayException, StripeException {
        return new ResponseEntity<>(paymentService.initiatePayment(), HttpStatus.OK);
    }
}
