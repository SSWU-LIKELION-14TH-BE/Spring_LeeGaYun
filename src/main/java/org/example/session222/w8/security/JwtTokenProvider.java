package org.example.session222.w8.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.expiration}")
    private long validityInMilliseconds;

    // 객체 생성 후, 시크릿 키 인코딩
    @PostConstruct
    protected void init() {
        this.secretKey = Base64.getEncoder().encodeToString
                (secretKey.getBytes());
    }

    // 🔑 JWT 토큰 생성
    public String createToken(String email) {
        Claims claims = Jwts.claims().setSubject(email);
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey) // 암호화
                .compact();
    }
    // 🔍 토큰에서 사용자 이메일 추출
    public String getEmail(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // 🔍 요청 헤더에서 토큰 꺼내기
    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
                // "Bearer"라는 접두사를 떼고 실제 토큰만 가져오기
        if (bearerToken != null && bearerToken.startsWith
                ("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    // ✅ 토큰 유효성 검사 (진위성, 기간 등 검사)
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            // 기간이 만료되었거나 변조되었다면 false
            return false;
        }
    }
}
