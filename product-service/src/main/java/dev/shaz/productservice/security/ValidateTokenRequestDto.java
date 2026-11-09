package dev.shaz.productservice.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateTokenRequestDto {
    private Long userId;
    private String token;
}
