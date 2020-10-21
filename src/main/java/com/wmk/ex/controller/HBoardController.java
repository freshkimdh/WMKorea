package com.wmk.ex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wmk.ex.service.ReviewBoardService;
import com.wmk.ex.vo.ReviewBoardVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@AllArgsConstructor
@Log4j
public class HBoardController {
	
	 private ReviewBoardService rservice;
	 
	//핫플레이스 게시판
	@RequestMapping("/hotReviewList")
	public String hotReviewList2(ReviewBoardVO reviewBoardVO, Model model) {
		log.info("review_boardList...");
		model.addAttribute("rList", rservice.getrList(reviewBoardVO));
		log.info(reviewBoardVO);
			
			
		return "/hot_board/hotReviewList";
	}
	 
}
