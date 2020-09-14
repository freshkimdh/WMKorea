package com.wmk.ex;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

/**
 * Handles requests for the application home page.
 */
@Log4j
@Controller
public class wmk_HomeController {
	
//	@RequestMapping("/index")
//	public String index() { 
//		log.info("index...");
//		return "index";
//	}
	
	@RequestMapping("/wmk_login/loginForm")
	public String login() { 
		log.info("loginForm...");
		return "wmk_login/loginForm";
	}
	
	@RequestMapping("/wmk_login/join")
	public String join() { 
		log.info("join...");
		return "wmk_login/join";
	}
	
	@GetMapping("/index") //왜 리퀘스트 매핑이 아닌 겟매핑인가?: 선생님 말이 잘 이해가 안된다.
	public String index(Model model) {
		log.info("index...");
		return "index";

	}
	
	
	@GetMapping("/loginForm") //왜 리퀘스트 매핑이 아닌 겟매핑인가?: 선생님 말이 잘 이해가 안된다.
	public String login(Model model) {
		
		log.info("loginForm...");
		return "loginForm";

	}
		
	@GetMapping("/joinForm") //왜 리퀘스트 매핑이 아닌 겟매핑인가?: 선생님 말이 잘 이해가 안된다.
	public String join(Model model) {
		
		log.info("joinForm...");
		return "joinForm";

	}
	
	@GetMapping("/mypage") //왜 리퀘스트 매핑이 아닌 겟매핑인가?: 선생님 말이 잘 이해가 안된다.
	public String myPage(Model model) {
		
		log.info("mypage...");
		return "myPage";

	}
	
}
