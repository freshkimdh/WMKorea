package com.wmk.ex.vo;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Getter
@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class CustomUser extends User {

	private UserVO user;
	//기본적으로 부모의 생성자를 호출해야만 정상적으로 작동.
	
	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		// TODO Auto-generated constructor stub
	}
	
	public CustomUser(UserVO userVO) {	
		
		super(userVO.getId(), userVO.getPw(),Collections
				    .singletonList(new SimpleGrantedAuthority(userVO.getAuthorities())));

		// TODO Auto-generated constructor stub
		this.user = userVO;
	}	
	
	
}

