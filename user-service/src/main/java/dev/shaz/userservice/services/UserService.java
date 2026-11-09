package dev.shaz.userservice.services;

import dev.shaz.userservice.dtos.UserDto;
import dev.shaz.userservice.models.Role;
import dev.shaz.userservice.models.User;
import dev.shaz.userservice.repositories.RoleRepository;
import dev.shaz.userservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public UserDto getUserDetails(Long userId){
        Optional<User> userOptional = userRepository.findById(userId);

        if(userOptional.isEmpty()){
            return null;
        }

        User user = userOptional.get();

        return UserDto.from(user);
    }

    public UserDto setUserRoles(Long userId, List<Long> roleIds){
        Optional<User> userOptional = userRepository.findById(userId);
        List<Role> roles = roleRepository.findAllByIdIn(roleIds);

        if(userOptional.isEmpty()){
            return null;
        }

        User user = userOptional.get();
        user.setRoles(Set.copyOf(roles));

        User savedUser = userRepository.save(user);

        return UserDto.from(savedUser);
    }
}
