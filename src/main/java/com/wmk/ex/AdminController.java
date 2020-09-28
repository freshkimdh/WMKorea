package com.wmk.ex;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wmk.ex.service.MemberService;
import com.wmk.ex.vo.MemberVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
//@RequestMapping("/ex/member/*")
public class AdminController {
	
	  
	// 회원 가입 get
	@RequestMapping(value = "/admin/index", method = RequestMethod.GET)
	public void getIndex() throws Exception {
	 log.info("get signup");
	}


	

	
}



