package com.gudee.urban.util;

import lombok.Data;

@Data
public class JwtVO {
	private String accessToken; 
	private String refreshToken;
}
