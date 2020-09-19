package com.wmk.ex;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wmk.ex.page.Criteria;
import com.wmk.ex.page.PageDTO;
import com.wmk.ex.service.BoardService;
import com.wmk.ex.vo.BoardVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

/**
 * Handles requests for the application home page.
 */

@Controller
@AllArgsConstructor
@Log4j
public class BoardController {
	
	

	//스프링 5는 생성자가 있으면 Auto(자동으로 넣어줌) 
	private BoardService service;
	
	 @GetMapping("/list") // get 방식으로 받아서 getmapping "@RequestMapping" 이것도 상관없음
	 public void list(Criteria cri, Model model) {	// 비지니스 로직이 들어간다 "BoardService"
		 log.info("list");
		 log.info(cri);
		 model.addAttribute("list", service.getList(cri));	
			System.out.println("미투");
		 int total = service.getTotal(cri);
		 log.info("total" + total);
		 
		 model.addAttribute("pageMaker", new PageDTO(cri,total));	
	 }
	
	@GetMapping("/content_view") 
	public String content_view(BoardVO boardVO, Model model) {
	   log.info("content_view");
	   model.addAttribute("content_view", service.get(boardVO.getbId()));

	   
	   return "content_view";
	}
	
	@GetMapping("/delete") 
	public String delete(BoardVO boardVO) {
	   log.info("delete");
	   
	    service.remove(boardVO.getbId());

	   
	    return "redirect:list";
	}
	
	@GetMapping("/write_view") 
	public String write_view() {
	   log.info("write_view");
	   return "write_view";
	}
	
	@RequestMapping("/write")
	public String write(BoardVO boardVO) {
		
		log.info("write");
		
		System.out.println("쓰자");
		
		service.writeBoard(boardVO);
		
		
		return "redirect:list";
	}
	
	
	@GetMapping("/reply_view") 
	public String reply_view(BoardVO boardVO, Model model) {
	
		log.info("reply_view");
		
		model.addAttribute("reply_view", service.get(boardVO.getbId()));
		
		return "reply_view";
	}
	
	@RequestMapping("/reply")
	public String reply(BoardVO boardVO, Model model) throws Exception {
		
		log.info("reply");
		
		service.writeReply(boardVO);
		
		
		return "redirect:list";
	}
	
	@RequestMapping("/modify")
	public String modify(BoardVO boardVO) {
		
		log.info("modify");
		
		service.modify(boardVO);
		
		return "redirect:list";
	}
	
	//검색기능
	@RequestMapping("/BoardByTitle")
	public String BoardByTitle(BoardVO boardVO, Model model) {  //무엇이든(제목이든, 이름이던...등등) 출력하고자 봉투를 인자로 넣음.
		
		System.out.println("나야나");
		System.out.println(boardVO.toString());  //BoardVO.java 에 있는 toString()을 출력하기 위해.(test용)
		log.info("BoardByTitle");
		
		service.BoardByTitle(boardVO.getbTitle());  //유저가 입력한 것을 서비스에서 받아주기위함.( 유저가 입력한 제목을 서비스로 넘기기 위함)
		System.out.println(service.BoardByTitle(boardVO.getbTitle()));
		
		model.addAttribute("BoardByTitle", service.BoardByTitle(boardVO.getbTitle()));
		
		return "list";
	}
	
	
}



