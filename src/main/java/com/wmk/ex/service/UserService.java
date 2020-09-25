package com.wmk.ex.service;

import javax.inject.Inject;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wmk.ex.mapper.UserMapper;
import com.wmk.ex.vo.UserVO;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@NoArgsConstructor
@Service
public class UserService {

	  @Inject
	   private BCryptPasswordEncoder passEncoder; //  н         ڵ     ϴ    ü

	   @Inject 
	   private UserMapper userMapper;
	     
	   public void addUser(UserVO userVO){ 
	      
	   String pw = userVO.getPw(); 
	   
	   String encode = passEncoder.encode(pw);
	     
	   userVO.setPw(encode);
	     
	   userMapper.insertUser(userVO); 
	   
	   userMapper.insertAuthorities(userVO);
	   
	   }  
	   @Transactional
	   public void userDelete(UserVO userVO) throws Exception {
	      log.info("delete Start");
	      String userId = userVO.getId();//hdhd
	      String userPw = userVO.getPw();//1234 암호
	      log.info("login ID   :   "+userVO.getId());
	      log.info("login PW   :   "+userVO.getPw());
	      userMapper.authori(userId);
	      userMapper.delMember(userVO);

	      log.info("delete end");
	   }

	   public UserVO getUserById(String id) {
	      if(id.isEmpty()) {
	         log.info("userId is empty");
	         return null;
	      }

	      return userMapper.readUser(id);
	   }
	   
	   public String getEncodePassword(String pw) {
	      log.info("pw"+pw);
	      return passEncoder.encode(pw);
	   }

	}