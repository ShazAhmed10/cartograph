package com.shazahmed.notificationservice.kafkaconsumers.userservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shazahmed.notificationservice.dtos.SendEmailMessageDto;
import com.shazahmed.notificationservice.utils.EmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

@Component
public class SendEmailConsumer {
    private ObjectMapper objectMapper;
    private EmailUtils emailUtil;
    private String emailPassword;

    @Autowired
    public SendEmailConsumer(EmailUtils emailUtil,
                             @Value("${email.password}") String emailPassword) {
        this.objectMapper = new ObjectMapper();
        this.emailUtil = emailUtil;
        this.emailPassword = emailPassword;
    }

    @KafkaListener(topics = "sendEmail", groupId = "emailService")
    public void listenGroupEmailService(String message) throws JsonProcessingException {
        System.out.println("Received message: " + message);

        SendEmailMessageDto emailMessage = objectMapper.readValue(message, SendEmailMessageDto.class);

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication
                        (
                                "project.notificationservice@gmail.com",
                                emailPassword
                        );
            }
        };

        Session session = Session.getInstance(props, auth);

        emailUtil.sendEmail(
                session,
                emailMessage.getTo(),
                emailMessage.getSubject(),
                emailMessage.getBody()
        );
    }
}
