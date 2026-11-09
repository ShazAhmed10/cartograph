package dev.shaz.userservice.dtos;

import dev.shaz.userservice.models.Role;
import dev.shaz.userservice.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserDto {
    private String email;
    private Set<Role> roles = new HashSet<>();

    //creating a deep copy
    public static UserDto from(User user){
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        //other attributes have to be set when required

        return userDto;
    }
}
