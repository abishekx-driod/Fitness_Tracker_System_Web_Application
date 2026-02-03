package com.example.fitness.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
	
    private static final String SECRET_KEY =
            "ABISHEK-PREMNATH-SARAVANAKUMAR-SIVASARAN";

    private final Key key =
            Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));


    private static final long EXPIRATION_TIME = 1000 * 60 * 60;

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    public String extractEmail(String token) {
        return getClaims(token).getSubject();
    }

    // ✅ Extract userId
    public Long extractUserId(String token) {
        return getClaims(token).get("userId", Long.class);
    }

    // ✅ Token validation
    public boolean isTokenValid(String token) {
        return !getClaims(token).getExpiration().before(new Date());
    }


    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
