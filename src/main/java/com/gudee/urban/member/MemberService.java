package com.gudee.urban.member;

import org.springframework.stereotype.Service;
import com.gudee.urban.dao.MemberMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {
	
	private final MemberMapper memberMapper;
	
	public void signUp(MemberVO memberVO) throws Exception {
		memberMapper.signUp(memberVO);
	}
	
	public MemberVO signIn(MemberVO memberVO) throws Exception {
		return memberMapper.signIn(memberVO);
	}
}
