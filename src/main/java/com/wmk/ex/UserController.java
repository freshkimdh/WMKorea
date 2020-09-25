package com.wmk.ex;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.wmk.ex.service.UserService;
import com.wmk.ex.vo.CustomUser;
import com.wmk.ex.vo.ResponseVO;
import com.wmk.ex.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@AllArgsConstructor
public class UserController {
	
	private UserService userService;
	@Inject
    private BCryptPasswordEncoder passEncoder;
	 
	@PostMapping("/addUser")
	public String adduser(UserVO userVO) {
	      
		log.info("post register");
		userService.addUser(userVO);
	      
	    return "redirect:/loginForm";	      
	}
	 //delete view ������ 
	   @GetMapping("/userDeleteView")
	    public String userDeleteView() {
	        log.info("welcome userDeleteView!");
	        return "user/UserDeleteView";
	    }
	   //ȸ�� Ż��
	    @PostMapping("user/userDelete")
	       @ResponseBody
	       public String userDelete(@RequestBody UserVO userVO, Authentication authentication, HttpServletRequest request) throws Exception {
	          Gson gson = new Gson();//** Gson���� ������ �α׸� �����ʰ� �ѹ��� ������ ��������..
	          // �α��ε� ������ ����
	           CustomUser loginInfo = (CustomUser) authentication.getPrincipal();
	           //loginInfo ���� ���� 
	           log.info("loginInfo:  "+loginInfo);
	           // DB�� ����Ǿ��ִ� ��й�ȣ�� ��ȣȭ�Ǿ��ִ� ��й�ȣ�� �� 
	           boolean isValidPassword = passEncoder.matches(userVO.getPw(), loginInfo.getUser().getPw());
	          log.info("true & fail isValidPassword   :  "+isValidPassword);
	          log.info("login ID      :  "+loginInfo.getUser().getId());
	          log.info("login password   :  "+userVO.getPw());
	          log.info("login Encoding password   :  "+loginInfo.getUser().getPw()); 
	          log.info(" true & fail   : "+isValidPassword+"  matches   :  "+userVO.getPw()+"     :     "+ loginInfo.getUser().getPw()); //matches(1234,$2a$10$R4UGHuNESie7gjG2TQhp9OHHHlfxUdWDyMKhXAj5lP8tECLORmIgW)
	          
	           if (isValidPassword) {                      //��� ���� true
	               userVO.setId(loginInfo.getUser().getId());   //userVO�� �α����� ���̵������� �־���
	               userVO.setPw(loginInfo.getUser().getPw());   //userVO�� �α����� ��ȣȭ �� password�� �־��� 
	               
	               //delete������ ���� 
	               userService.userDelete(userVO);
	               log.info("Delete success");
	               // �α׾ƿ� ó��
	               request.getSession().invalidate();//�ʱ�ȭ 
	               log.info("logout success ");

	               //@RequestBody�̱� ������ ������� ajax�� ����
	               //matches�Լ� ��� ���� ��� ����� 200,success�� ajax�� ���� 
	               return gson.toJson(new ResponseVO<>(200, "success"));    
	           }
	           //matches�Լ� ��� ������ ��� ����� 400,fail�� ajax�� ���� 
	           log.info("notValidPassword");
	           return gson.toJson(new ResponseVO<>(400, "fail"));

	       }
	}