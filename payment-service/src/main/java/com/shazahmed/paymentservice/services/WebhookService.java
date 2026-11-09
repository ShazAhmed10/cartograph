package com.shazahmed.paymentservice.services;

import com.stripe.model.Event;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class WebhookService {

    public void saveRazorpayPaymentDetails(Map<String,Object> payload){
        //Store crucial information to DB that is coming from the Webhook payload

    }

    public void saveStripePaymentDetails(Event event){
        //Store crucial information to DB that is coming from the Webhook payload

    }
}
