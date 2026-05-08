package com.lafachada.gateway.Service;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

import org.springframework.beans.factory.annotation.Value;

@Service
public class JwtService {

    @Value("${security.jwt.secret}")
    private String secretKeyString;

    private Key getSigninKey() {
        byte[] keyByte = Decoders.BASE64.decode(secretKeyString);
        return Keys.hmacShaKeyFor(keyByte);
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
        .setSigningKey(getSigninKey())
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
    }

    public boolean isTokenValid(String token, String username){
        return extractUsername(token).equals(username);
    }
}
