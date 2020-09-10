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
	public void Home() { // void�� "/admin/adminHome" ��� ������ ���� �� 
		log.info("adminHome...");
	}
	
	@GetMapping("/intro/introduction")
	public void introduction() { 
		log.info("introduction...");
	}
	
	@GetMapping("/index")
	public void index() { 
		log.info("index...");
	}
	
	@GetMapping("/login")
	public void login() { 
		log.info("login...");
	}
	
	@GetMapping("/join")
	public void join() { 
		log.info("join...");
	}
	
}
