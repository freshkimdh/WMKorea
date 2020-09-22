package com.wmk.ex.service;

import javax.inject.Inject;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.wmk.ex.mapper.UserMapper;
import com.wmk.ex.vo.UserVO;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Service
public class UserService {

	@Inject
	private BCryptPasswordEncoder passEncoder; // �н����� ���ڵ��� �ϴ� ��ü

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
