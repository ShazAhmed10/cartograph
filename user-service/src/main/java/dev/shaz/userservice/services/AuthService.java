package dev.shaz.userservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.shaz.userservice.dtos.SendEmailMessageDto;
import dev.shaz.userservice.dtos.UserDto;
import dev.shaz.userservice.models.Role;
import dev.shaz.userservice.models.Session;
import dev.shaz.userservice.models.SessionStatus;
import dev.shaz.userservice.models.User;
import dev.shaz.userservice.repositories.SessionRepository;
import dev.shaz.userservice.repositories.UserRepository;
import dev.shaz.userservice.security.JwtData;
import dev.shaz.userservice.thirdpartyclients.kafka.KafkaProducerClient;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.util.*;

@Service
public class AuthService {
    private UserRepository userRepository;
    private SessionRepository sessionRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private SecretKey secretKey;
    private KafkaProducerClient kafkaProducerClient;
    private ObjectMapper objectMapper;

    @Autowired
    public AuthService(UserRepository userRepository,
                       SessionRepository sessionRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder,
                       KafkaProducerClient kafkaProducerClient,
                       ObjectMapper objectMapper){
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.kafkaProducerClient = kafkaProducerClient;
        this.objectMapper = objectMapper;

        this.secretKey = Jwts.SIG.HS256.key().build();
    }

    // Note: This method should return a custom object containing token, headers, etc
    // For now, to avoid creating an object, directly returning ResponseEntity from here
    public ResponseEntity<UserDto> logIn(String email, String password){
        Optional<User> userOptional = userRepository.findByEmail(email);

        if(userOptional.isEmpty()){
            return null;
        }
        User user = userOptional.get();

        if(! bCryptPasswordEncoder.matches(password, user.getPassword())){
            throw new RuntimeException("The Password provided is incorrect");
        }

        //String token = RandomStringUtils.randomAlphanumeric(30);

        HashMap<String, Object> jwtData = new HashMap<>();
        jwtData.put("email", email);
        jwtData.put("roles", List.of(user.getRoles()));
        jwtData.put("createdAt", new Date());
        jwtData.put("expiryAt", new Date(LocalDate.now().plusDays(3).toEpochDay()));

        String token = Jwts
                .builder()
                .claims(jwtData)
                .signWith(secretKey)
                .compact();

        Session session = new Session();
        session.setUser(user);
        session.setToken(token);
        session.setSessionStatus(SessionStatus.ACTIVE);

        sessionRepository.save(session);

        UserDto userDto = UserDto.from(user);

        MultiValueMap<String, String> headers = new MultiValueMapAdapter<>(new HashMap<>());
        headers.add(HttpHeaders.SET_COOKIE, "auth-token:" + token);

        return new ResponseEntity<UserDto>(userDto, headers, HttpStatus.OK);
    }

    public void logOut(Long userId, String token){
        Optional<Session> sessionOptional = sessionRepository.findByTokenAndUser_Id(token, userId);

        if(sessionOptional.isEmpty()){
            return;
        }

        Session session = sessionOptional.get();
        session.setSessionStatus(SessionStatus.ENDED);

        sessionRepository.save(session);
    }

    public UserDto signUp(String email, String password) throws JsonProcessingException {
        User user = new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));

        User savedUser = userRepository.save(user);

        SendEmailMessageDto sendEmailMessageDto = new SendEmailMessageDto();
        sendEmailMessageDto.setTo(savedUser.getEmail());
        sendEmailMessageDto.setSubject("Welcome Email");
        sendEmailMessageDto.setBody("Hi, thank you for signing up with us !");

        kafkaProducerClient.sendMessage("sendEmail",
                objectMapper.writeValueAsString(sendEmailMessageDto));

        return UserDto.from(savedUser);
    }

    public JwtData validate(Long userId, String token){
        JwtData jwtData = new JwtData();

        Optional<Session> sessionOptional = sessionRepository.findByTokenAndUser_Id(token, userId);

        if(sessionOptional.isEmpty()){
            jwtData.setSessionStatus(SessionStatus.ENDED);
            return jwtData;
        }

        Session session = sessionOptional.get();

        if(session.getSessionStatus().equals(SessionStatus.ENDED)){
            jwtData.setSessionStatus(SessionStatus.ENDED);
            return jwtData;
        }

        Jws<Claims> claimsJws = Jwts
                .parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token);

        String email = (String) claimsJws.getPayload().get("email");
        jwtData.setEmail(email);

//        List<String> roles =  (List<String>) claimsJws.getPayload().get("roles");
//        jwtData.setRoles(roles);

        return jwtData;
    }
}
