package com.wmk.ex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.wmk.ex.service.FBoardService;
import com.wmk.ex.vo.CustomUser;
import com.wmk.ex.vo.FBoardVO;
import com.wmk.ex.vo.LiketoVO;
import com.wmk.ex.vo.ResponseVO;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/commons/")
@Log4j
public class CommonController {
	@Autowired
	FBoardService fBoardService;
	
	
	

	@PostMapping(value="/board/like/{board_no}")
	public ResponseEntity<String> updateLike(@PathVariable("board_no")int board_no,Authentication authentication){
		log.info("호출 컨드롤러는 commons 실행 메소드는 updatelike()");
		Gson gson = new Gson();
        CustomUser loginInfo = (CustomUser) authentication.getPrincipal();
        log.info("loginInfo:  "+loginInfo);
        
		ResponseEntity<String> entity = null;
	
		try {
			fBoardService.updateInsertLike(board_no,loginInfo.getUser().getId());
			
			log.info("aa");
			entity = new ResponseEntity<>("success",HttpStatus.OK);
			log.info("ssssss");
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@PostMapping(value="/board/unlike/{board_no}")
	public ResponseEntity<String> updateunLike(@PathVariable("board_no")int board_no,Authentication authentication){
		log.info("호출 컨드롤러는 commons 실행 메소드는 updatelike()");
		ResponseEntity<String> entity = null;
		Gson gson = new Gson();
        CustomUser loginInfo = (CustomUser) authentication.getPrincipal();
        log.info("loginInfo:  "+loginInfo);
        
		try {
			// 스프링 유저정보 받아와서  unLike실행
			fBoardService.deleteUnlike(board_no,loginInfo.getUser().getId());
			entity = new ResponseEntity<String>("success",HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
}
