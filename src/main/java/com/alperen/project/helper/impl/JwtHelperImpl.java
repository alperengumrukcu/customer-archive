package com.alperen.project.helper.impl;

import com.alperen.project.helper.interfaces.JwtHelper;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class JwtHelperImpl implements JwtHelper {

    private Algorithm ALGORITHM;

    public JwtHelperImpl (@Value("${jwt.secret.key}") String SECRETKEY) {
        ALGORITHM = Algorithm.HMAC256(SECRETKEY);;
    }

    @Override
    public String generateToken(Long userId) {
        Instant instant = Instant.now();

        return JWT.create()
                .withClaim("id",userId)
                .withExpiresAt(instant.plus(30, ChronoUnit.DAYS))
                .sign(ALGORITHM);
    }

    @Override
    public Long getUserIDinToken(String token) {
        JWTVerifier jwtVerifier = JWT.require(ALGORITHM).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        return decodedJWT.getClaim("id").asLong();
    }
}