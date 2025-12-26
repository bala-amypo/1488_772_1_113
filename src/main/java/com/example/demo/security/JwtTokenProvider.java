package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private static final String SECRET =
            "MyJwtSecretKeyMyJwtSecretKeyMyJwtSecretKey"; // ≥ 32 chars

    private static final long EXPIRATION = 86400000; // 1 day

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    // ✅ TOKEN GENERATION (FIXED)
    public String generateToken(Authentication authentication) {

        UserDetails userPrincipal =
                (UserDetails) authentication.getPrincipal();

        return Jwts.builder()
                .subject(userPrincipal.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // ✅ EXTRACT USERNAME
    public String getUsernameFromJwt(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    // ✅ VALIDATE TOKEN
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parse(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
