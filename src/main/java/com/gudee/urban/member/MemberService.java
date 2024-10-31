package com.gudee.urban.member;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.gudee.urban.dao.MemberMapper;
import com.gudee.urban.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {
	
	private final MemberMapper memberMapper;
	@Value("${jwt.secret}")
	private String secretKey;
	private Long expiredMs = 1000 * 60 * 60L;
	
	public void signUp(MemberVO memberVO) throws Exception {
		memberMapper.signUp(memberVO);
	}
	
	public MemberVO signIn(MemberVO memberVO) throws Exception {
		return memberMapper.signIn(memberVO);
	}
	
	public String token(String username, String password) throws Exception {
	
		return JwtUtil.JwtCreate(username, secretKey, expiredMs);
	}
}
