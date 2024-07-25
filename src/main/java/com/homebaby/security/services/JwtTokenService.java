package com.homebaby.security.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.homebaby.errors.exceptions.AuthenticationTokenCreationException;
import com.homebaby.errors.exceptions.UnauthorizedException;
import com.homebaby.security.dto.UserDetailsDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.UUID;

@Service
public class JwtTokenService {

    @Value("${home-care-baby.jwt.secret}")
    private String secret;

    @Value("${home-care-baby.jwt.expiration}")
    private Long expiration;

    @Value("${home-care-baby.jwt.issuer}")
    private String issuer;

    private final ZoneOffset zoneOffset = ZoneOffset.of("-03:00");


    public String generateToken(UserDetailsDTO user){
        try{
            var algorithm = Algorithm.HMAC256(this.secret);

            return JWT.create()
                    .withIssuer(this.issuer)
                    .withSubject(user.getUser().getId().toString())
                    .withIssuedAt(LocalDateTime.now().toInstant(zoneOffset))
                    .withExpiresAt(LocalDateTime.now().plusMinutes(this.expiration).toInstant(zoneOffset))
                    .withPayload(Map.of("user", user.toMap()))
                    .sign(algorithm);
        }catch (JWTCreationException jwtCreationException){
            throw new AuthenticationTokenCreationException(jwtCreationException);
        }
    }

    public UUID getUserId(String token){
        try{
            var algorithm = Algorithm.HMAC256(this.secret);

            String id = JWT.require(algorithm)
                    .withIssuer(this.issuer)
                    .build()
                    .verify(token)
                    .getSubject();

            return UUID.fromString(id);

        }catch (JWTVerificationException jwtVerificationException){
            throw new UnauthorizedException(jwtVerificationException);
        }
    }
}
