package com.shazahmed.paymentservice.configurations.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class Producer {
    private KafkaTemplate<String, String> kafkaTemplate;
    private NewTopic topic;

    @Autowired
    public Producer(KafkaTemplate<String, String> kafkaTemplate,
                    NewTopic topic){
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    public void sendMessage(String msg) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic.name(), msg);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message=[" + msg +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" +
                        msg + "] due to : " + ex.getMessage());
            }
        });
    }
}
