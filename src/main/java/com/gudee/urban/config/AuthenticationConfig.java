package com.gudee.urban.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.gudee.urban.member.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity // 모든 api에 인증이 필요하다고 Security가 디폴트로 적용한다
public class AuthenticationConfig {
	
	private final MemberService memberService;
	
	@Value("${jwt.secret}")
	private String secretKey; 
	
	@Bean
	//public 을 선언하면 default로 바꾸라는 메세지 출력
	WebSecurityCustomizer webSecurityConfig() {
		//Security에서 무시해야하는 URL 패턴 등록
		return web -> web
			   .ignoring()
			   .requestMatchers("/apartments/**")
			   .requestMatchers("/imag/**")
			   .requestMatchers("/fonts/**")
			   .requestMatchers("/css/**")
			   .requestMatchers("/js/**")
			   .requestMatchers("/favicon/**")
			   .requestMatchers("/resources/**");
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.httpBasic(h -> h.disable()) // 인증을 ui가 아니고 token으로 할거기 때문에 basic이 아니고 disable로 설정
			.csrf(csrf -> csrf.disable())
			.cors(cors -> cors.disable())
			.sessionManagement(
				(sessionManagement) ->		
					sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // JWT사용할 경우 사용
			
		//권한에 관련된 설정
		httpSecurity.authorizeHttpRequests(
					(authorizeRequest) ->
						authorizeRequest
							.requestMatchers("/").permitAll()
							.requestMatchers("/member/signin", "/member/signup", "/member/token").permitAll()
//							.requestMatchers("/member/review").permitAll()
							.requestMatchers("/member/review").authenticated()
//							.requestMatchers(HttpMethod.POST, "/member/**").authenticated() //member로 들어오는  post메서드 요청은 다 막는다.
							
				);
		httpSecurity.addFilterBefore(new JwtFilter(memberService, secretKey), UsernamePasswordAuthenticationFilter.class);
		
					
		
		return httpSecurity.build();
	}
}
