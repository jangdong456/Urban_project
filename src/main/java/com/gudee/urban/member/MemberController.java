package com.gudee.urban.member;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	public ResponseEntity<String> signIn(MemberVO memberVO) throws Exception {
//		MemberVO member = memberService.signIn(memberVO);
		String result = "로그인 성공";
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping("signup")
	public String signUp() throws Exception {
		
		return "signup";
	}
	
	@PostMapping("signup")
	public ResponseEntity<String> signUp(MemberVO memberVO) throws Exception {
//		memberService.signUp(memberVO);
		String result = "회원가입성공";
		return ResponseEntity.ok().body(result);
	}
	
	@PostMapping("token")
	public ResponseEntity<String> token() throws Exception {
		return ResponseEntity.ok().body(memberService.token("", ""));
	}
	
	@PostMapping("review")
	public ResponseEntity<String> review() throws Exception {
		
		return ResponseEntity.ok().body("리뷰등록이 되었습니다.");
	}
}
