package com.gudee.urban.member;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.gudee.urban.dao.MemberMapper;
import com.gudee.urban.util.JwtUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class MemberService {
	
	private final MemberMapper memberMapper;
	@Value("${jwt.secret}")
	private String secretKey;
	private Long expiredMs = 1000 * 60 * 60L;
	
	public int signUp(MemberVO memberVO) throws Exception {
		
		return memberMapper.signUp(memberVO);
	}
	
	public String signIn(MemberVO memberVO) throws Exception {
		MemberVO member = memberMapper.signIn(memberVO);
		
		if(member != null) {
			String username = member.getUsername();
			String password = member.getPassword();
			String token = token(username,password);
			log.info("token : {} ",token);
			
			return token;
		}
		String meassge = null;
		return meassge;
	}
	
	public String token(String username, String password) throws Exception {
	
		return JwtUtil.JwtCreate(username, secretKey, expiredMs);
	}
}
