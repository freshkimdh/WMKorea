package com.wmk.ex;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class AdminController {
	
	@GetMapping("/admin/adminHome")
	public void adminhome() {
		log.info("Welcome admin!");
	}
}
