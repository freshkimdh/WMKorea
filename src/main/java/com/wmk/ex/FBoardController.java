package com.wmk.ex;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wmk.ex.service.FBoardService;
import com.wmk.ex.vo.BoardVO;
import com.wmk.ex.vo.FBoardVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@AllArgsConstructor
@Log4j
public class FBoardController {
	
	private FBoardService service;
	
	//게시판 목록
	@RequestMapping("/free_boardList")
	public String boardList(FBoardVO fboardVO, Model model) {
		
		log.info("boardList...");
		model.addAttribute("list", service.getList());
		
		return "/free_board/boardList";
	}
	
	//게시판 내용
	@GetMapping("/free_contentView") 
	public String contentView(FBoardVO fboardVO, Model model) {
		
	   log.info("content_view...");
	   model.addAttribute("contentView", service.getNum(fboardVO.getfBoard_Num()));
	   model.addAttribute("list", service.getList());
	   
	   return "/free_board/contentView";
	}
	
	//게시판 작성 view
	@RequestMapping("/free_writeView")
	public String writeView() {
		
		log.info("free_writeView...");
		
		return "/free_board/writeView";
	}
	
}



