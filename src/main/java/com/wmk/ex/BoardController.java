package com.wmk.ex;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wmk.ex.service.BoardService;
import com.wmk.ex.vo.BoardVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@AllArgsConstructor
@Log4j
public class BoardController {
	
	private BoardService service;
	
	/////////////This controller is written by Chaddy ///////////////////
	/////////////정경채가 새로운 게시판에 작업한 컨트롤러 부분///////////////////
	
	@RequestMapping("/boardList")
	public String boardList(BoardVO boardVO,Model model) {
		
		log.info("/wmk_board/boardList");
		model.addAttribute("list", service.getList());
		
		return "/wmk_board/boardList";
	}
	
	
	@RequestMapping("/writeView")
	public String writeView(BoardVO boardVO) {
		
		log.info("/wmk_board/writeView");
		
		return "/wmk_board/writeView";
	}
	
	
	@RequestMapping("/write2")
	public String write2(BoardVO boardVO) {
		
		log.info("write2");
		service.writeBoard(boardVO);
		
		return "redirect:boardList";
	}
	
	
	@GetMapping("/contentView") 
	public String contentView(BoardVO boardVO, Model model) {
		
	   log.info("content_view");
	   model.addAttribute("contentView", service.get(boardVO.getbId()));
	   model.addAttribute("list", service.getList());
	   
	   return "/wmk_board/contentView";
	}
	
	
	@GetMapping("/delete2") 
	public String delete2(BoardVO boardVO) {
		
	    log.info("delete2");
	    service.remove(boardVO.getbId());
	   
	    return "redirect:boardList";
	}
	
	
	@GetMapping("/replyView") 
	public String replyView(BoardVO boardVO, Model model) {
	
		log.info("replyView");		
		model.addAttribute("replyView", service.get(boardVO.getbId()));
		
		return "/wmk_board/replyView";
	}
	
	
	@RequestMapping("/reply2")
	public String reply2(BoardVO boardVO, Model model) throws Exception {
		
		log.info("reply2");	
		service.writeReply(boardVO);
	
		return "redirect:boardList";
	}
	
	
	@GetMapping("/modifyView") 
	public String modifyView(BoardVO boardVO, Model model) {
	
		log.info("modifyView");	
		model.addAttribute("modifyView", service.get(boardVO.getbId()));
		
		return "/wmk_board/modifyView";
	}
	
	
	@RequestMapping("/modify2")
	public String modify2(BoardVO boardVO) {
		
		log.info("modify2");
		service.modify(boardVO);
		
		return "redirect:boardList";
	}
	
}



