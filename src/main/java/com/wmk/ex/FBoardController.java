package com.wmk.ex;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wmk.ex.page.Criteria;
import com.wmk.ex.page.PageDTO;
import com.wmk.ex.service.FBoardService;
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
	public String boardList(FBoardVO fboardVO, Model model, Criteria cri) {
		
		log.info("boardList...");
		log.info(cri);
		model.addAttribute("list", service.getListWithPaging(cri));
		
		int total = service.getTotalCount(cri);
		log.info("total" + total);
		 
		model.addAttribute("pageMaker", new PageDTO(cri,total));
		
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
	
	//게시판 작성 
	@RequestMapping("/free_write")
	public String write(FBoardVO fboardVO) throws Exception {
		
		log.info("free_write...");
		service.writeBoard(fboardVO);

		return "redirect:free_boardList";
	}
	
	//게시판 수정
	@GetMapping("/free_modifyView") 
	public String modifyView(FBoardVO fboardVO, Model model) {
	
		log.info("modifyView...");	
		model.addAttribute("modifyView", service.getNum(fboardVO.getfBoard_Num()));
		
		return "/free_board/modifyView";
	}
	
	//게시판 수정
	@RequestMapping("/free_modify")
	public String modify(FBoardVO fboardVO) {
		
		log.info("free_modify...");
		service.updateModify(fboardVO);
		
		return "redirect:free_boardList";
	}
	
	//게시판 삭제
	@GetMapping("/free_delete") 
	public String delete(FBoardVO fboardVO) {
		
	    log.info("free_delete...");
	    service.deleteBoard(fboardVO.getfBoard_Num());
	   
	    return "redirect:free_boardList";
	}
}



