//package com.gudee.urban.config;
//
//import java.io.IOException;
//import java.util.List;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import com.gudee.urban.member.MemberService;
//import com.gudee.urban.util.JwtUtil;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@RequiredArgsConstructor
//public class JwtFilter extends OncePerRequestFilter {
//			
//	private final MemberService memberService;
//	private final String secretKey;
//	
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//	
//	final String authoriztion = request.getHeader(HttpHeaders.AUTHORIZATION);
//	log.info("authoriztion : {}", authoriztion);
//	
//	// token 없으면 실행
//	if(authoriztion == null || !authoriztion.startsWith("Bearer ") ) {
//		log.error("authoriztion을 잘못보냈습니다.");;
//		filterChain.doFilter(request, response);
//		
//		return;
//	}
//	
//	//Token 꺼내기
//	String token = authoriztion.split(" ")[1];
//	
//	//Token Expired(유효시간) 되었는지 여부
//	try {
//		if(JwtUtil.isExpried(token, secretKey)) {
//			log.error("Token이 만료 되었습니다.");
//			filterChain.doFilter(request, response);
//			return;
//		}
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (ServletException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	
//	
//	// userName Token에서 꺼내기
//	String userName = "";	
//		
//	//권한부여
//	UsernamePasswordAuthenticationToken authenticationToken =
//			new UsernamePasswordAuthenticationToken(userName, null, List.of(new SimpleGrantedAuthority("USER"))); 
//			// List.of(new SimpleGrantedAuthority("USER") -> db에서 role를 "USER"로 잡아놓을시 
//	
//	//Detail
//	authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//	SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//	filterChain.doFilter(request, response);
//
//	}
//	
//	
//}
