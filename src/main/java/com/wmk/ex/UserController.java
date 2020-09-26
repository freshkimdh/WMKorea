package com.wmk.ex;

import java.io.IOException;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.wmk.ex.service.UserService;
import com.wmk.ex.vo.CustomUser;
import com.wmk.ex.vo.KakaoProfile;
import com.wmk.ex.vo.OAuthToken;
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
	    
	    
	    
	    
	    @GetMapping("/auth/kakao/callback")
	    public @ResponseBody String kakaoCallback(String code) { //@ResponseBody data를 리턴해주는 컨트롤러 함수
	    	
	    	//POST 방식으로 key=value 데이터를 요청 (카카오쪽으로)
	    	RestTemplate rt = new RestTemplate();
	    	
	    	//HttpHeaders 오브젝트 생성
	    	HttpHeaders headers = new HttpHeaders();
 	    	headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
	    	
	    	//HttpBody 오브젝트 생성
	    	MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
 	    	params.add("grant_type", "authorization_code");
	    	params.add("client_id", "af9546b83fbd65051801d2e327f8c259");
	    	params.add("redirect_uri", "http://localhost:8282/ex/auth/kakao/callback");
	    	params.add("code", code);
	    	
	    	//HttpHeader와 HttpBody를 하나의 오브젝트에 담기
	    	HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest = new HttpEntity<>(params, headers);
	    	
	    	//Http 요청하기 - post 방식으로 - 그리고 response 변수의 응답 받음.
	    	ResponseEntity<String> response = rt.exchange(
	    			"https://kauth.kakao.com/oauth/token",
	    			HttpMethod.POST,
	    			kakaoTokenRequest,
	    			String.class	
	    			);
	    	
	    	ObjectMapper objectMapper = new ObjectMapper();	    	
	    	OAuthToken oauthToken = null;    	
	    	try {
				oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
			} catch (JsonParseException e) {			
				e.printStackTrace();
			} catch (JsonMappingException e) {		
				e.printStackTrace();
			} catch (IOException e) {	
				e.printStackTrace();
			}
	    	
	    	System.out.println("카카오 엑세스 토큰:" + oauthToken.getAccess_token());
	    	
	    	
	    	
	    	RestTemplate rt2 = new RestTemplate();
	    	
	    	//HttpHeaders 오브젝트 생성
	    	HttpHeaders headers2 = new HttpHeaders();
	    	headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
 	    	headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
	    	
	    	//HttpHeader와 HttpBody를 하나의 오브젝트에 담기
	    	HttpEntity<MultiValueMap<String,String>> kakaoProfileRequest2 = new HttpEntity<>(headers2);
	    	
	    	//Http 요청하기 - post 방식으로 - 그리고 response 변수의 응답 받음.
	    	ResponseEntity<String> response2 = rt2.exchange(
	    			"https://kapi.kakao.com/v2/user/me",
	    			HttpMethod.POST,
	    			kakaoProfileRequest2,
	    			String.class	
	    			);
		
	    	
	    	System.out.println(response2.getBody());
	    	
	    	ObjectMapper objectMapper2 = new ObjectMapper();	    	
	    	KakaoProfile kakaoProfile = null;    	
	    	try {
	    		kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
			} catch (JsonParseException e) {			
				e.printStackTrace();
			} catch (JsonMappingException e) {		
				e.printStackTrace();
			} catch (IOException e) {	
				e.printStackTrace();
			}
	    	
	    	//User 오브젝트: id, pw, email
	    	System.out.println("카카오 아이디(번호): " + kakaoProfile.getId());
	    	System.out.println("카카오 아이디(이메일): " + kakaoProfile.getKakao_account().getEmail());
	    	
	    	System.out.println("블로그서버 유저네임:" + kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId());
	    	System.out.println("블로그서버 이메일: " + kakaoProfile.getKakao_account().getEmail());
	    	UUID garbagePassword = UUID.randomUUID();
	    	System.out.println("블로그서버 패스워드: " + garbagePassword);
	    	
//	    	User user = User.builder()
//	    			.id(kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId())
//	    			.pw(garbagePassword.toString())
//	    			.email(kakaoProfile.getKakao_account().getEmail())
//	    			.build();
//	    	
//	    	userService.회원찾기();
//	    	
//	    	
//	    	userService.회원가입(user);
//	    	
//	    	
	    	return response2.getBody(); 
	    	
	    }
	    
	    
	    
	    
	    
	    
	}