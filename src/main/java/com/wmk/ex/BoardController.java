package com.wmk.ex;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wmk.ex.page.Criteria;
import com.wmk.ex.page.PageDTO;
import com.wmk.ex.service.BoardService;
import com.wmk.ex.vo.BoardVO;
import com.wmk.ex.vo.ReplyVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

/**
 * Handles requests for the application home page.
 */

@Controller
@AllArgsConstructor
@Log4j
public class BoardController {
	
	

	//������ 5�� �����ڰ� ������ Auto(�ڵ����� �־���) 
	private BoardService service;
	
	 @GetMapping("/list") // get ������� �޾Ƽ� getmapping "@RequestMapping" �̰͵� �������
	 public void list(Criteria cri, Model model) {	// �����Ͻ� ������ ���� "BoardService"
		 log.info("list");
		 log.info(cri);
		 model.addAttribute("list", service.getList(cri));	
	
		 int total = service.getTotal(cri);
		 log.info("total" + total);
		 
		 model.addAttribute("pageMaker", new PageDTO(cri,total));	
	 }
	
	@GetMapping("/content_view") 
	public String content_view(BoardVO boardVO, Model model) {
	   log.info("content_view");
	   model.addAttribute("content_view", service.get(boardVO.getbId()));
	   
	   List<ReplyVO> replyList = service.readReply(boardVO.getbId());
	   model.addAttribute("replyList", replyList);

	   
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
		
		System.out.println("����");
		
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
	
	//�˻���� 
	@RequestMapping("/boardByTitle")
	public String boardByTitle(BoardVO boardVO, Model model) {  //�����̵�(�����̵�, �̸��̴�...���) ����ϰ��� ������ ���ڷ� ����.
		
		System.out.println(boardVO.toString());  //BoardVO.java �� �ִ� toString()�� ����ϱ� ����.(test��)
		log.info("boardByTitle");
		
		service.boardByTitle(boardVO);  //������ �Է��� ���� ���񽺿��� �޾��ֱ�����.( ������ �Է��� ������ ���񽺷� �ѱ�� ����)
		System.out.println(service.boardByTitle(boardVO));
		
		model.addAttribute("boardByTitle", service.boardByTitle(boardVO));
		
		return "list";
	}
	
	/////////////This controller is written by Chaddy ///////////////////
	/////////////����ä�� ���ο� �Խ��ǿ� �۾��� ��Ʈ�ѷ� �κ�///////////////////
	
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



