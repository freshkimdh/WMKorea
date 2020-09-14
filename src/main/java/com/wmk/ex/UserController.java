package com.wmk.ex;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wmk.ex.service.UserService;
import com.wmk.ex.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;



/**
 * Handles requests for the application home page.
 */
@Log4j
@Controller
@AllArgsConstructor
public class UserController {
	
	
	private UserService userService;
	
	
	@GetMapping(value = "/user/userForm") public void userForm() {
	  
		log.info("welcome userform!"); }
	 
	
	@PostMapping("/addUser")
	public String adduser(UserVO userVO) {
	      
		log.info("post register");
	      
	      userService.addUser(userVO);
	      
	      return "redirect:/loginForm";
	      
	} 
	
}
