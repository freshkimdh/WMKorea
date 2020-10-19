package com.wmk.ex.controller;

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
public class MemberController {
	
	@Inject
	MemberService service;

	@Autowired
	BCryptPasswordEncoder passEncoder;
	  
	// ȸ�� ���� get
	@RequestMapping(value = "/member/signup", method = RequestMethod.GET)
	public void getSignup() throws Exception {
	 log.info("get signup");
	}

	// ȸ�� ���� post
	@RequestMapping(value = "/member/signup", method = RequestMethod.POST)
	public String postSignup(MemberVO vo) throws Exception {
	 log.info("post signup");
	  
	 String inputPass = vo.getUserPass();
	 String pass = passEncoder.encode(inputPass);
	 vo.setUserPass(pass);

	 service.signup(vo);

	 return "redirect:/";
	}
	
	
	// �α���  get
	@RequestMapping(value = "/member/signin", method = RequestMethod.GET)
	public void getSignin() throws Exception {
	 log.info("get signin");
	}

	// �α��� post
	@RequestMapping(value = "/member/signin", method = RequestMethod.POST)
	public String postSignin(MemberVO vo, HttpServletRequest req, RedirectAttributes rttr) throws Exception {
	 log.info("post signin");
	   
	 MemberVO login = service.signin(vo);  
	 HttpSession session = req.getSession();
	 
	 boolean passMatch = passEncoder.matches(vo.getUserPass(), login.getUserPass());
	 
	 if(login != null && passMatch) {
	  session.setAttribute("member", login);
	 } else {
	  session.setAttribute("member", null);
	  rttr.addFlashAttribute("msg", false);
	  return "redirect:/member/signin";
	 }  
	 
	 return "redirect:/index";
	}
	  
	// �α׾ƿ�
	@RequestMapping(value = "/member/signout", method = RequestMethod.GET)
	public String signout(HttpSession session) throws Exception {
	 log.info("get logout");
	 
	 service.signout(session);
	   
	 return "redirect:/";
	}
	

	
}



