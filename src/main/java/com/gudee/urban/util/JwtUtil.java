package com.gudee.urban.util;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
//@RequiredArgsConstructor
public class JwtUtil {
		
	@Value("${jwt.secret}")
	private String secretKey;
	
	
    private  SecretKey getKey() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
	
	//토큰 유효성 검증
	public boolean isExpried(String token) throws Exception {
		
		boolean result = true;
		
		try {
			result = Jwts.parserBuilder()
				.setSigningKey(getKey())
				.build()
				.parseClaimsJws(token)
				.getBody().getExpiration()
				.before(new Date());
		} catch (Exception e) {
			log.info("토큰유효 메서드 부분 에러 메시지 :" + e);
		}
		
		return result;
	}
	
	//토큰 생성
	public String JwtCreate(String username, Long expiredMs) throws Exception {
			
		Claims claims = Jwts.claims();
		claims.put("username", username);
		
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expiredMs))
				.signWith(getKey(),SignatureAlgorithm.HS256)
				.compact();
	}
}
