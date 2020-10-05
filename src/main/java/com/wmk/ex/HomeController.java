package com.wmk.ex;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.log4j.Log4j;

//�귣ġ test
@Log4j
@Controller
public class HomeController {
	
	@GetMapping("/index")
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
		return "/wmk_home/myPage";
	}
	
	//----------------------------------------------------------------------------------//
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		log.info("Welcome home! The client locale is {}.");
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
}
