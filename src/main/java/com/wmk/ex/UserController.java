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
	 //delete view 페이지 
	   @GetMapping("/userDeleteView")
	    public String userDeleteView() {
	        log.info("welcome userDeleteView!");
	        return "user/UserDeleteView";
	    }
	   //회원 탈퇴
	    @PostMapping("user/userDelete")
	       @ResponseBody
	       public String userDelete(@RequestBody UserVO userVO, Authentication authentication, HttpServletRequest request) throws Exception {
	          Gson gson = new Gson();//** Gson으로 일일히 로그를 찍지않고 한번에 정보를 볼수있음..
	          // 로그인된 유저의 정보
	           CustomUser loginInfo = (CustomUser) authentication.getPrincipal();
	           //loginInfo 유저 정보 
	           log.info("loginInfo:  "+loginInfo);
	           // DB에 저장되어있는 비밀번호와 암호화되어있는 비밀번호를 비교 
	           boolean isValidPassword = passEncoder.matches(userVO.getPw(), loginInfo.getUser().getPw());
	          log.info("true & fail isValidPassword   :  "+isValidPassword);
	          log.info("login ID      :  "+loginInfo.getUser().getId());
	          log.info("login password   :  "+userVO.getPw());
	          log.info("login Encoding password   :  "+loginInfo.getUser().getPw()); 
	          log.info(" true & fail   : "+isValidPassword+"  matches   :  "+userVO.getPw()+"     :     "+ loginInfo.getUser().getPw()); //matches(1234,$2a$10$R4UGHuNESie7gjG2TQhp9OHHHlfxUdWDyMKhXAj5lP8tECLORmIgW)
	          
	           if (isValidPassword) {                      //결과 값은 true
	               userVO.setId(loginInfo.getUser().getId());   //userVO에 로그인한 아이디정보를 넣어줌
	               userVO.setPw(loginInfo.getUser().getPw());   //userVO에 로그인한 암호화 된 password를 넣어줌 
	               
	               //delete쿼리문 수행 
	               userService.userDelete(userVO);
	               log.info("Delete success");
	               // 로그아웃 처리
	               request.getSession().invalidate();//초기화 
	               log.info("logout success ");

	               //@RequestBody이기 때문에 결과값만 ajax에 리턴
	               //matches함수 결과 참일 경우 결과값 200,success만 ajax에 리턴 
	               return gson.toJson(new ResponseVO<>(200, "success"));    
	           }
	           //matches함수 결과 거짓일 경우 결과값 400,fail만 ajax에 리턴 
	           log.info("notValidPassword");
	           return gson.toJson(new ResponseVO<>(400, "fail"));

	       }
	}