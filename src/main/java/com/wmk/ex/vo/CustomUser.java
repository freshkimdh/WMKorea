package com.wmk.ex.vo;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomUser extends User {

	//기본적으로 부모의 생성자를 호출해야만 정상적으로 작동.
	private UserVO user;
	
	
	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		
		super(username, password, authorities);
	}
	
	
	public CustomUser(UserVO userVO) {	
		
		super(userVO.getId(), userVO.getPw(),Collections
				    .singletonList(new SimpleGrantedAuthority(userVO.getAuthorities())));
		this.user = userVO;
	}	
	
	
}

