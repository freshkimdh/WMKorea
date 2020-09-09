package com.wmk.ex;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class LoginController {
	
	
	@GetMapping("/login/loginForm")
	public void loginFor() {
		log.info("adminHome...");
	}
	
	@GetMapping("/login/accessDenied")
	public void accessDenied() {
		log.info("Welcome Access Denied!");
	}
}
