package com.abpgroup.majumundur.security;

import com.abpgroup.majumundur.model.entity.AppUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    @Value("${app.MajuMundur.jwt.secret-key}")
    private String jwtSecretKey;

    @Value("${app.MajuMundur.jwt.app-name}")
    private String jwtAppName;

    @Value("${app.MajuMundur.jwt.expiration-time}")
    private long jwtExpirationTime;

    private Algorithm algorithm;

    @PostConstruct
    private void initAlgorithm() {
        this.algorithm = Algorithm.HMAC512(jwtSecretKey);
    }

    public String generateToken(AppUser appUser) {
        String token= JWT.create()
                .withIssuer(jwtAppName)
                .withSubject(appUser.getUsername())
                .withExpiresAt(Instant.now().plusSeconds(jwtExpirationTime))
                .withIssuedAt(Instant.now())
                .withClaim("role", String.valueOf(appUser.getRole()))
                .sign(algorithm);
        return token;
    }

    public boolean verifyJwtToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(jwtAppName)
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getIssuer().equals(jwtAppName);
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("Invalid JWT Token");
        }
    }

    public Map<String,String> getUserInfoByToken(String token) {
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(jwtAppName)
                .build();
        DecodedJWT decodedJWT = verifier.verify(token);
        Map<String,String> userInfo=new HashMap<>();
        userInfo.put("username",decodedJWT.getSubject());
        userInfo.put("role",decodedJWT.getClaim("role").asString());
        return userInfo;
    }
}
