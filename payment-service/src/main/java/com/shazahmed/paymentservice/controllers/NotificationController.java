package com.shazahmed.paymentservice.controllers;

import com.shazahmed.paymentservice.dtos.SendEmailRequestDto;
import com.shazahmed.paymentservice.configurations.kafka.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paymentNotification")
public class NotificationController {
    private Producer producer;

    @Autowired
    public NotificationController(Producer producer) {
        this.producer = producer;
    }

    @PostMapping
    public void sendEmail(@RequestBody SendEmailRequestDto requestDto){
        producer.sendMessage(requestDto.getMessage());
    }
}
