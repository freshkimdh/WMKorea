package com.wmk.ex.service;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.wmk.ex.mapper.UserMapper;
import com.wmk.ex.vo.UserVO;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@NoArgsConstructor
@Service
public class UserService {

	@Inject
	private BCryptPasswordEncoder passEncoder; // 패스워드 인코딩을 하는 객체

	@Inject 
	private UserMapper userMapper;
	  
	  
	public void addUser(UserVO userVO){ 
		
	String pw = userVO.getPw(); 
	
	String encode = passEncoder.encode(pw);
	  
	userVO.setPw(encode);
	  
	userMapper.insertUser(userVO); 
	
	userMapper.insertAuthorities(userVO);
	
	} 
	 

}
