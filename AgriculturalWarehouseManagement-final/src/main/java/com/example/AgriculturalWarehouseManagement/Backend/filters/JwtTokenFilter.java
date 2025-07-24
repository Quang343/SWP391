package com.example.AgriculturalWarehouseManagement.Backend.filters;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;


@Component
public class JwtTokenFilter  {

    private final SecretKey secretKey;

    private final long expirationMs = 20 * 60 * 1000; // 20 phút

    public JwtTokenFilter() {
        String rawKey = "spring-boot-react-spring-boot-react-123456";
        byte[] keyBytes = rawKey.getBytes(java.nio.charset.StandardCharsets.UTF_8);
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationMs);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims decodeToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody(); // Trả về thông tin (claims) từ token
        } catch (Exception e) {
            return null; // Nếu token không hợp lệ, trả về null
        }
    }
}