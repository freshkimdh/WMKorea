package com.wmk.ex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;


@Log4j
@Controller
@RequestMapping("/wmk_home")
public class HomeController {
	
	@GetMapping("/index")
	public void Index(Model model) {
		log.info("index...");
	}
	
	@GetMapping("/characterMaking")
	public void Character(Model model) {
		log.info("character...");
	}
	
	@GetMapping("/loginForm") 
	public void Login(Model model) {
		log.info("loginForm...");
	}
		
	@GetMapping("/joinForm") 
	public void Join(Model model) {		
		log.info("joinForm...");
	}
	
	@GetMapping("/mypage") 
	public void MyPage(Model model) {
		log.info("mypage...");
	}
	
}
