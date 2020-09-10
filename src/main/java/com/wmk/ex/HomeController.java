package com.wmk.ex;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;


import lombok.extern.log4j.Log4j;

/**
 * Handles requests for the application home page.
 */
@Log4j
@Controller
public class HomeController {
	
	@GetMapping("/home")
	//=@RequestMapping(method = RequestMethod.GET).
	public void Home() { // void는 "/admin/adminHome" 경로 가지고 오는 것 
		log.info("adminHome...");
	}
	
	@GetMapping("/intro/introduction")
	public void introduction() { 
		log.info("loginForm...");
	}
	
	@GetMapping("/index")
	public void index() { 
		log.info("loginForm...");
	}
	
	@GetMapping("/login")
	public void login() { 
		log.info("loginForm...");
	}
	
	@GetMapping("/join")
	public void join() { 
		log.info("loginForm...");
	}
	
}
