package com.shazahmed.paymentservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendEmailRequestDto {
    private String from;
    private String to;
    private String subject;
    private String message;
}
