package dev.shaz.productservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests((authorize) -> authorize
                                .requestMatchers("/actuator/health").permitAll()
                                //.anyRequest().authenticated()
                                .anyRequest().permitAll()
                )
                .formLogin(Customizer.withDefaults())
                .oauth2ResourceServer((oauth2) -> oauth2
                        .jwt(Customizer.withDefaults()));

        return http.build();
    }
}
