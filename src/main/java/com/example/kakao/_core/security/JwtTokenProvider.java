package com.example.kakao._core.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.kakao.users.StringArrayConverter;
import com.example.kakao.users.User;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;


@Component
public class JwtTokenProvider {
    private static final Long EXP = 30 * 60 * 1000L;
    public static final String TOKEN_PREFIX = "Bearer "; // 스페이스 필요함
    public static final String HEADER = "Authorization";
    private static final String SECRET = "MySecretKey";

    public static String create(User user) {
        StringArrayConverter sac = new StringArrayConverter();
        String roles = sac.convertToDatabaseColumn(user.getRoles());
        String jwt = JWT.create()
                .withSubject(user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXP))
                .withClaim("id", user.getUserId())
                .withClaim("role", roles)
                .sign(Algorithm.HMAC512(SECRET));
        return TOKEN_PREFIX + jwt;
    }

    public static DecodedJWT verify(String jwt) throws SignatureVerificationException, TokenExpiredException {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(SECRET))
                .build().verify(jwt);
        return decodedJWT;
    }

}
