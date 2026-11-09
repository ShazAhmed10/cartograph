package dev.shaz.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserServiceUserDto {
    private String email;
    private Set<UserServiceRole> roles = new HashSet<>();
}
