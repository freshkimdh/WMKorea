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
import org.springframework.web.bind.annotation.RequestParam;
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
	 //delete view ������ 
	   @GetMapping("/userDeleteView")
	    public String userDeleteView() {
	        log.info("welcome userDeleteView!");
	        return "user/UserDeleteView";
	    }
	   //회원탈퇴
	    @PostMapping("user/userDelete")
	       @ResponseBody
	       public String userDelete(@RequestBody UserVO userVO, Authentication authentication, HttpServletRequest request) throws Exception {
	          Gson gson = new Gson();
	           CustomUser loginInfo = (CustomUser) authentication.getPrincipal();
	           log.info("loginInfo:  "+loginInfo);
	           boolean isValidPassword = passEncoder.matches(userVO.getPw(), loginInfo.getUser().getPw());
	          log.info("true & fail isValidPassword   :  "+isValidPassword);
	          log.info("login ID      :  "+loginInfo.getUser().getId());
	          log.info("login password   :  "+userVO.getPw());
	          log.info("login Encoding password   :  "+loginInfo.getUser().getPw()); 
	          log.info(" true & fail   : "+isValidPassword+"  matches   :  "+userVO.getPw()+"     :     "+ loginInfo.getUser().getPw()); //matches(1234,$2a$10$R4UGHuNESie7gjG2TQhp9OHHHlfxUdWDyMKhXAj5lP8tECLORmIgW)
	          
	           if (isValidPassword) {                 
	               userVO.setId(loginInfo.getUser().getId());  
	               userVO.setPw(loginInfo.getUser().getPw());  
	               
	               userService.userDelete(userVO);
	               log.info("Delete success");
	               
	               request.getSession().invalidate();
	               log.info("logout success ");

	               return gson.toJson(new ResponseVO<>(200, "success"));    
	           }
	           log.info("notValidPassword");
	           return gson.toJson(new ResponseVO<>(400, "fail"));

	       }
	    //회원가입 아이디 중복체크 
	    @GetMapping(value = "/user/check")
	    @ResponseBody
	    public String checkSameId(@RequestParam("id") String id) {
	        Gson gson = new Gson();
	        log.info("Login ID  :  "+id);
	        try {
	            if (id.isEmpty()) {
	            	log.info("id.isEmpty :  "+id.isEmpty());
	                return gson.toJson(new ResponseVO<>(401, false));
	            }
	            
	            UserVO userVO = userService.getUserById(id);
	            log.info("UserVO = null ? notnull? : "+userVO);
	            if (userVO != null) { 
	                return gson.toJson(new ResponseVO<>(400, false));
	            }

	        } catch (Exception e) {
	            return gson.toJson(new ResponseVO<>(500,false));
	        }
	        return gson.toJson(new ResponseVO<>(200, true));

	    }
	    
	    
	    
	    @GetMapping("/auth/kakao/callback")
	    public @ResponseBody String kakaoCallback(String code) { //@ResponseBody data�� �������ִ� ��Ʈ�ѷ� �Լ�
	    	
	    	//POST ������� key=value �����͸� ��û (īī��������)
	    	RestTemplate rt = new RestTemplate();
	    	
	    	//HttpHeaders ������Ʈ ����
	    	HttpHeaders headers = new HttpHeaders();
 	    	headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
	    	
	    	//HttpBody ������Ʈ ����
	    	MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
 	    	params.add("grant_type", "authorization_code");
	    	params.add("client_id", "af9546b83fbd65051801d2e327f8c259");
	    	params.add("redirect_uri", "http://localhost:8282/ex/auth/kakao/callback");
	    	params.add("code", code);
	    	
	    	//HttpHeader�� HttpBody�� �ϳ��� ������Ʈ�� ���
	    	HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest = new HttpEntity<>(params, headers);
	    	
	    	//Http ��û�ϱ� - post ������� - �׸��� response ������ ���� ����.
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
	    	
	    	System.out.println("īī�� ������ ��ū:" + oauthToken.getAccess_token());
	    	
	    	
	    	
	    	RestTemplate rt2 = new RestTemplate();
	    	
	    	//HttpHeaders ������Ʈ ����
	    	HttpHeaders headers2 = new HttpHeaders();
	    	headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
 	    	headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
	    	
	    	//HttpHeader�� HttpBody�� �ϳ��� ������Ʈ�� ���
	    	HttpEntity<MultiValueMap<String,String>> kakaoProfileRequest2 = new HttpEntity<>(headers2);
	    	
	    	//Http ��û�ϱ� - post ������� - �׸��� response ������ ���� ����.
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
	    	
	    	//User ������Ʈ: id, pw, email
	    	System.out.println("īī�� ���̵�(��ȣ): " + kakaoProfile.getId());
	    	System.out.println("īī�� ���̵�(�̸���): " + kakaoProfile.getKakao_account().getEmail());
	    	
	    	System.out.println("��α׼��� ��������:" + kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId());
	    	System.out.println("��α׼��� �̸���: " + kakaoProfile.getKakao_account().getEmail());
	    	UUID garbagePassword = UUID.randomUUID();
	    	System.out.println("��α׼��� �н�����: " + garbagePassword);
	    	
//	    	User user = User.builder()
//	    			.id(kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId())
//	    			.pw(garbagePassword.toString())
//	    			.email(kakaoProfile.getKakao_account().getEmail())
//	    			.build();
//	    	
//	    	userService.ȸ��ã��();
//	    	
//	    	
//	    	userService.ȸ������(user);
//	    	
//	    	
	    	return response2.getBody(); 
	    	
	    }
	    
	    
	    
	    
	    
	    
	}