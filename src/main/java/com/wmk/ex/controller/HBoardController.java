package com.wmk.ex.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wmk.ex.page.Criteria;
import com.wmk.ex.page.PageDTO;
import com.wmk.ex.service.RBoardService;
import com.wmk.ex.vo.CustomUser;
import com.wmk.ex.vo.RBoardVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@AllArgsConstructor
@Log4j
public class HBoardController {
	
	 private RBoardService rservice;
	 
	//�Խ��� ���
		@RequestMapping("/hotReviewList")
		public String hotReviewList2(RBoardVO rboardVO, Model model, String rArea) {
			log.info("rArea??	;" + rArea);
			log.info("review_boardList...");
			model.addAttribute("rList", rservice.getReviewList(rboardVO));
			model.addAttribute("rArea", rboardVO.getrArea());
			log.info("rArea, rboardVO.getrArea()");
			
			
			return "/hot_board/hotReviewList";
		}
	 
}
