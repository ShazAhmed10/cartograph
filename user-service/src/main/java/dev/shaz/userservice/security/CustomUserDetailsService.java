package dev.shaz.userservice.security;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.shaz.userservice.models.User;
import dev.shaz.userservice.repositories.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Getter
@Setter
@JsonDeserialize(as = CustomUserDetailsService.class)
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(username);
        if (optionalUser.isEmpty()){
            throw new UsernameNotFoundException("The given Username is not present");
        }
        User user = optionalUser.get();

        return new CustomUserDetails(user);
    }
}
