package dev.shaz.userservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendEmailMessageDto {
    private String to;
    private String subject;
    private String body;
}
