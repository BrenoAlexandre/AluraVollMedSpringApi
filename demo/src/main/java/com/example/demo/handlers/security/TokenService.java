package com.example.demo.handlers.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.example.demo.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    private String issuer = "apiAluraSpring";

    public String createAuthToken(User user) throws RuntimeException {
        try{
            Algorithm algorithm = Algorithm.HMAC256(this.secret);
            return JWT.create()
                    .withIssuer(this.issuer)
                    .withSubject(user.getLogin())
                    .withClaim("id", user.getId())
                    .withExpiresAt(getExpirationDate())
                    .sign(algorithm);
        } catch(JWTCreationException exception) {
            throw new RuntimeException("An error occurred issuing JWT token", exception);
        }
    }

    private Instant getExpirationDate() {
        return LocalDateTime.now().plusHours(3)
                .toInstant(ZoneOffset.of("-03:00"));
    }

    public String decodeAuthToken(String JWTToken) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer(this.issuer).build()
                    .verify(JWTToken).getSubject();
        }catch (JWTDecodeException exception) {
            throw new JWTDecodeException("Invalid authorization token: ", exception);
        }
    }
}
