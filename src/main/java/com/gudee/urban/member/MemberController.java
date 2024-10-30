package com.gudee.urban.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	
	private final MemberService memberService;
	
	@GetMapping("signin")
	public String signIn() throws Exception {
		return "signin";
	}
	
	@PostMapping("signin")
	public void signIn(MemberVO memberVO) throws Exception {
		MemberVO member = memberService.signIn(memberVO);		
	}
	
	@GetMapping("signup")
	public String signUp() throws Exception {
		
		return "signup";
	}
	
	@PostMapping("signup")
	public void signUp(MemberVO memberVO) throws Exception {
		memberService.signUp(memberVO);

	}
}
