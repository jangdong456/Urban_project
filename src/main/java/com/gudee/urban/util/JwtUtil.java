package com.gudee.urban.util;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
//@RequiredArgsConstructor
public class JwtUtil {
		

//(secretKey).build().parseClaimsJws(token).getBody().getExpiration();
	public static boolean isExpried(String token, String secretKeyArg) throws Exception {
		
		SecretKey secretKey = Keys.hmacShaKeyFor(secretKeyArg.getBytes(StandardCharsets.UTF_8));
//        Jwts.parserBuilder()
//                .setSigningKey(secretKey)    // 비밀값으로 복호화
//                .build()
//                .parseClaimsJws(token);
		return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getExpiration().before(new Date());
		
	}
	
	public static String JwtCreate(String username, String secretKey, Long expiredMs) throws Exception {
		
//		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
// 		Key key = Keys.hmacShaKeyFor(keyBytes);
		Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
		
		
		Claims claims = Jwts.claims();
		claims.put("username", username);
		
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expiredMs))
				.signWith(key) // , SignatureAlgorithm.HS256
				.compact();
	}
	
	
}
