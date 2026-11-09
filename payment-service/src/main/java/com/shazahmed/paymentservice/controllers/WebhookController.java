package com.shazahmed.paymentservice.controllers;

import com.shazahmed.paymentservice.services.WebhookService;
import com.stripe.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/webhooks")
public class WebhookController {
    private WebhookService webhookService;

    @Autowired
    public WebhookController(WebhookService webhookService){
        this.webhookService = webhookService;
    }

    @PostMapping("/razorpay")
    public void forAllRazorpayEvents(@RequestBody Map<String,Object> payload){
        webhookService.saveRazorpayPaymentDetails(payload);
    }

    @PostMapping("/stripe")
    public void forAllStripeEvents(Event event){
        webhookService.saveStripePaymentDetails(event);
    }
}
