package com.wmk.ex;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.log4j.Log4j;

/**
 * Handles requests for the application home page.
 */

@Log4j
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
		
		//누구나 접근 가능 하도록
	   @RequestMapping("/login/loginForm")
	   public String loginForm(Locale locale, Model model) {
		   log.info("loginForm...");
	      return "login/loginForm";
	   }
	   
	   @RequestMapping("/admin/adminHome")
	   public String adminHome(Locale locale, Model model) {
		   log.info("adminHome...");
	      return "admin/adminHome";
	   }
	   
	   @RequestMapping("/intro/introduction")
	   public String intro(Locale locale, Model model) {
		   log.info("intro...");
	      return "intro/introduction";
	   }
	   
	   @RequestMapping("/accessDenied")
	   public String accessDenied(Locale locale, Model model) {
		   log.info("accessDenied...");
	      return "login/accessDenied";
	   }

	
	
}
