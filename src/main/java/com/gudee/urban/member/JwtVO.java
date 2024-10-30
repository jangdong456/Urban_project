package com.gudee.urban.member;

import lombok.Data;

@Data
public class JwtVO {
	private String accessToken; 
	private String refreshToken;
}
