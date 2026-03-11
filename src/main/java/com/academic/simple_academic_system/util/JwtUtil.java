package com.academic.simple_academic_system.util;

import com.academic.simple_academic_system.model.request.BaseRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.SecretKey;

@Service
public class JwtUtil {

    private final SecretKey secretKey;

    public JwtUtil(@Value("${app.jwt.secret-key}") String secretKey) {
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token.replace("Bearer ", ""))
                .getBody();
    }

    public <T extends BaseRequest> void extractJwt(String token, T request) {
        if (ObjectUtils.isEmpty(token)) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Silahkan untuk login");
        Claims claims = extractClaims(token);
        request.setRole(claims.get("role", String.class));
        request.setUsername(claims.get("username", String.class));
    }
}
