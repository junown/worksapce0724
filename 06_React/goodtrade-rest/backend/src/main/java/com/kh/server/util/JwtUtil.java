package com.kh.server.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret:your-secret-key-must-be-at-least-256-bits-long-for-hs256-algorithm}")
    private String secret;

    @Value("${jwt.expiration:86400000}") // 기본 24시간 (밀리초)
    private Long expiration;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    // JWT 토큰 생성
    public String generateToken(Long userId, String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .subject(String.valueOf(userId))
                .claim("username", username)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(getSigningKey())
                .compact();
    }

    // JWT 토큰에서 사용자 ID 추출
    public Long getUserIdFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return Long.parseLong(claims.getSubject());
    }

    // JWT 토큰에서 사용자명 추출
    public String getUsernameFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.get("username", String.class);
    }

    // JWT 토큰에서 Claims 추출
    private Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // JWT 토큰 유효성 검증
    public boolean validateToken(String token) {
        try {
            getClaimsFromToken(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // JWT 토큰 만료 여부 확인
    public boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return true;
        }
    }
}

