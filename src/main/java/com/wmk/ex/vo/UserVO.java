package com.wmk.ex.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserVO {

	private String id;			//ID			VARCHAR2(50 BYTE)
	private String pw;			//PW			VARCHAR2(100 BYTE)
	private String nickname;	//NICKNAME		VARCHAR2(50 BYTE)
	private String email;		//EMAIL			VARCHAR2(50 BYTE)
	private String nationality;	//NATIONALITY	VARCHAR2(50 BYTE)
	private int enabled;		//ENABLED		CHAR(1 BYTE)
	private String login_Type;	//LOGIN_TYPE	VARCHAR2(100 BYTE)
	
	
	
	public String getAuthorities() {
		
		String authorities = "ROLE_USER";
		
		return authorities;
	}

	
}
