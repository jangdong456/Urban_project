package com.gudee.urban.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.gudee.urban.member.MemberService;
import com.gudee.urban.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter {
			
	private final MemberService memberService;
	private final JwtUtil jwtUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	
    // 1. 토큰이 필요하지 않은 API URL에 대해서 배열로 구성한다.
    List<String> list = Arrays.asList(
            "/member/signin",
            "/favicon.ico",
            "/",
            "/resources/**",
            "/apartments/**"
           
    );
   
   if (list.contains(request.getRequestURI())) {
	   System.out.println(request.getRequestURI());
	   System.out.println("확인");
        filterChain.doFilter(request, response);
        return;
    } 
   	
   final Cookie[] cookies = request.getCookies();
   
   
   String token = "";
   
   if(cookies != null) {
	   for(Cookie cookie : cookies) {
		   if("token".equals(cookie.getName())) {
			   token = cookie.getValue();

		   }
	   }	   
   }

	
	// token 없으면 실행
	if(token == null) {
		log.error("token이 없습니다.");;
		filterChain.doFilter(request, response);
		return;
	}
	
	
	//Token Expired(유효시간) 되었는지 여부
	try {
		if(jwtUtil.isExpried(token)) {
			log.error("Token이 만료 되었습니다.");
			filterChain.doFilter(request, response);
			return;
		}
		System.out.println("토큰이 유효합니다@@@@@@@@@@@@");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ServletException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	// userName Token에서 꺼내기
	String userName = "";	
		
	//권한부여
	UsernamePasswordAuthenticationToken authenticationToken =
			new UsernamePasswordAuthenticationToken(userName, null, List.of(new SimpleGrantedAuthority("USER"))); 
			// List.of(new SimpleGrantedAuthority("USER") -> db에서 role를 "USER"로 잡아놓을시 
	
	//Detail
	authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	filterChain.doFilter(request, response);

	}
	
	
}
