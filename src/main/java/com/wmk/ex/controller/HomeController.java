package com.wmk.ex.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wmk.ex.service.UserService;

import lombok.extern.log4j.Log4j;


@Log4j
@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value="index")
	public String index(Model model) {
		log.info("index...");
		
		return "/wmk_home/index";
	}
	
	
	
	@GetMapping("/characterMaking")
	public String character(Model model) {
		log.info("character...");
		return "/wmk_home/characterMaking";
	}
	
	
	@GetMapping("/loginForm") 
	public String login(Model model) {
		log.info("loginForm...");
		return "/wmk_home/loginForm";
	}
		
	@GetMapping("/joinForm") 
	public String join(Model model) {		
		log.info("joinForm...");
		return "/wmk_home/joinForm";
	}
	
	
	@GetMapping("/mypage") 
	public String myPage(Model model) {
		log.info("mypage...");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		String userId = ((UserDetails)principal).getUsername(); 
		System.out.println("userIduserId" + userId);
		
		if(userId == null || userId.equals("")) {
			model.addAttribute("userDetail", "");  
		}else{
			model.addAttribute("userDetail", userService.readUser(userId));  
		}
		return "/wmk_home/myPage";
	}
		
	
	@GetMapping("tiles")
	public String layout(Model model) {
		return "layout";
	}
	

	@GetMapping("/accessDenied")
	public String accessDenied(Model model) {
		return "accessDenied";
	}
	
	@GetMapping("/loginFail")
	public String loginFail(Model model) {
		return "loginFail";
	}
	
	@GetMapping("/modifyFail")
	public String modifyFail() {
		return "modifyFail";
	}
	
	@GetMapping("seoulList")
	public String seoulList(Model model) {
		return "seoulList";
	}
	
	@GetMapping("seoulCotentView")
	public String seoulCotentView(Model model) {
		return "seoulCotentView";
	}
}
