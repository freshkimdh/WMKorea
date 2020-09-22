package com.wmk.ex;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.wmk.ex.service.UserService;
import com.wmk.ex.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@AllArgsConstructor
public class UserController {
	
	private UserService userService;
	 
	@PostMapping("/addUser")
	public String adduser(UserVO userVO) {
	      
		log.info("post register");
		userService.addUser(userVO);
	      
	    return "redirect:/loginForm";	      
	} 
	
}
