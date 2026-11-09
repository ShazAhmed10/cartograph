package dev.shaz.userservice.security;

import dev.shaz.userservice.models.SessionStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JwtData {
    private String email;
    private List<String> roles;
    private SessionStatus sessionStatus;
}
