package com.wmk.ex;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class HomeController {
	
	@GetMapping("/index")
	public String index(Model model) {
		log.info("index...");
		return "/wmk_home/index";
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
		return "/wmk_home/myPage";
	}
	
}
