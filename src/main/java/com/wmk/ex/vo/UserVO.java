package com.wmk.ex.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Getter
@Setter
@AllArgsConstructor
public class UserVO {

	private String id;//	ID	VARCHAR2(50 BYTE)
	private String pw;//	PW	VARCHAR2(100 BYTE)
	private String nickname;//	NICKNAME	VARCHAR2(50 BYTE)
	private String email;//	EMAIL	VARCHAR2(50 BYTE)
	private String nationality;//	NATIONALITY	VARCHAR2(50 BYTE)
	private int enabled;//	ENABLED	CHAR(1 BYTE)
	
	public UserVO() {
		
		this("id", "pw", "nickname", "email", "nationality", 1);
		
	}
	
	public String getAuthorities() {
		String authorities = "ROLE_USER";
		

		
		return authorities;
	}

	
	
}
