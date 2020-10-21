package com.wmk.ex.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.log4j.Log4j;


@Log4j
@Controller
public class HomeController {
	

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	  public String hello(Locale locale, Model model) {

	  
	  return "tiles";
	  }

	
	@RequestMapping(value="index", method = RequestMethod.GET)
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
	
	//아래 코드 삭제 예정
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value="tiles", method = RequestMethod.GET)
	public String layout(Model model) {
		return "layout";
	}
	
	@RequestMapping(value="/accessDenied", method = RequestMethod.GET)
	public String accessDenied(Model model) {
		return "accessDenied";
	}
	
	@RequestMapping(value="/loginFail", method = RequestMethod.GET)
	public String loginFail(Model model) {
		return "loginFail";
	}
	
	
}
