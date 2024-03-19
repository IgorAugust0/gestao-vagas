package com.igor.gestao_vagas.providers;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

// import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.igor.gestao_vagas.security.SecretKeyGenerator;

@Service
public class JWTProvider {

    // @Value("${security.token.secret}")
    // private String secretKey;

    private final String secretKey;

    public JWTProvider() {
        this.secretKey = SecretKeyGenerator.generateSecretKey();
    }

    public String generateToken(String subject) {

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var token = JWT.create()
                .withIssuer("gestao_vagas")
                .withSubject(subject)
                .withExpiresAt(Instant.now().plus(Duration.ofMinutes(5)))
                .withIssuedAt(Instant.now())
                .sign(algorithm);

        return token;
    }

    // overload do método para adicionar claims e expire
    public String generateToken(String subject, String claimName, List<String> claimValue, Instant expiresIn) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var token = JWT.create()
                .withIssuer("gestao_vagas")
                .withSubject(subject)
                .withClaim(claimName, claimValue)
                .withExpiresAt(expiresIn)
                .withIssuedAt(Instant.now())
                .sign(algorithm);

        return token;
    }

    public String validateToken(String token) {
        token = token.replace("Bearer ", "");

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        try {
            var subject = JWT.require(algorithm).build().verify(token).getSubject();
            return subject;
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return "";
        }
    }
}
