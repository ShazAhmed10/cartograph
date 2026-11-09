package dev.shaz.productservice.security;

import dev.shaz.productservice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TokenValidator {
    @Value("${authService.api.validateurl}")
    private String validateUrl;
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    public TokenValidator(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public JwtData validateToken(String authToken, Long userId) throws NotFoundException{
        RestTemplate restTemplate = restTemplateBuilder.build();

        ValidateTokenRequestDto requestDto = new ValidateTokenRequestDto();
        requestDto.setToken(authToken);
        requestDto.setUserId(userId);

        ResponseEntity<JwtData> response = restTemplate.postForEntity(validateUrl, requestDto, JwtData.class);

        JwtData jwtData = response.getBody();

        if(jwtData == null){
            throw new NotFoundException("Token cannot be Authenticated");
        }

//        Boolean flag = false;
//        for(String role : jwtData.getRoles()){
//            if(role.equals("Admin")){
//                flag = true;
//            }
//        }
//
//        if(flag.equals(false)){
//            throw new NotFoundException("Role permission not found");
//        }

        return jwtData;
    }
}
