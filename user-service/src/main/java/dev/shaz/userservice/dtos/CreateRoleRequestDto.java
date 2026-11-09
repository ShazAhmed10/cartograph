package dev.shaz.userservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SecondaryRow;

@Getter
@Setter
public class CreateRoleRequestDto {
    private String name;
}
