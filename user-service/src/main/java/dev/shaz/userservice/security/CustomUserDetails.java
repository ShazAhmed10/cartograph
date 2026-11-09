package dev.shaz.userservice.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.shaz.userservice.models.Role;
import dev.shaz.userservice.models.User;
import dev.shaz.userservice.security.services.CustomGrantedAuthority;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonDeserialize(as = CustomUserDetails.class)
public class CustomUserDetails implements UserDetails {
    private User user;

    public CustomUserDetails(User user){
        this.user = user;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(Role role: user.getRoles()){
            authorities.add(new CustomGrantedAuthority(role));
        }

        return authorities;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
