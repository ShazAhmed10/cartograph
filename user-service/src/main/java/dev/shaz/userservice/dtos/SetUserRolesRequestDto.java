package dev.shaz.userservice.dtos;

import dev.shaz.userservice.models.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SetUserRolesRequestDto {
    private List<Long> roleIds;
}
