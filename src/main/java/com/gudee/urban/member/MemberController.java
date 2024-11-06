package com.gudee.urban.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	@ResponseBody
	public ResponseEntity<Map<String,Object>> signIn(@RequestBody MemberVO memberVO) throws Exception {
	
		String token = memberService.signIn(memberVO);
		
		HttpStatus status;
		String message;
		

		if(token != null) {
			log.info("로그인성공");
			status = HttpStatus.OK;
			message = "로그인 성공";

		}else {
			log.info("로그인실패");
			status = HttpStatus.UNAUTHORIZED;
			message = "로그인 실패";
		}
		
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("status", status.value());
        response.put("token", token);
        
//        ResponseEntity:  text형식인데 ResponseEntity이걸 사용해서 응답값을 json형식으로 보내려면 Map을 사용해야함
//        Map 으로 데이터를 보내야 자바스크립 딴에서 res.json()으로 받을수 있음
        
        return new ResponseEntity<>(response,status);
	}
	 
	@GetMapping("signup")
	public String signUp() throws Exception {
		
		return "signup";
	}
	
	@PostMapping("signup")
	public ResponseEntity<Map<String, Object>> signUp(@RequestBody MemberVO memberVO) throws Exception {
		
		System.out.println(memberVO.getUsername());
		System.out.println(memberVO.getPassword());
//		int success = memberService.signUp(memberVO);
//		System.out.println("반환값 확인 : " + success);
		String result = "회원가입성공";
		
		return ResponseEntity.ok().body(Map.of("message", result));
	}
	
	@PostMapping("token")
	public ResponseEntity<String> token() throws Exception {
		String token = memberService.token("", "");
		log.info("token : {}", token);
		return ResponseEntity.ok().body(memberService.token("", ""));
	}
	
	@PostMapping("review")
	public ResponseEntity<String> review() throws Exception {
		
		return ResponseEntity.ok().body("리뷰등록이 되었습니다.");
	}
}
