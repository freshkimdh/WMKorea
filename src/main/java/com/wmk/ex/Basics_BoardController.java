package com.wmk.ex;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wmk.ex.page.Criteria;
import com.wmk.ex.page.PageDTO;
import com.wmk.ex.service.BoardService;
import com.wmk.ex.vo.BoardVO;
import com.wmk.ex.vo.ReplyVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@AllArgsConstructor
@Log4j
public class Basics_BoardController {
	
	private BoardService service;
	
	 @GetMapping("/list") 
	 public void list(Criteria cri, Model model) {	
		 
		 log.info("list");
		 log.info(cri);
		 model.addAttribute("list", service.getList(cri));	
	
		 int total = service.getTotal(cri);
		 log.info("total" + total);
		 
		 model.addAttribute("pageMaker", new PageDTO(cri,total));	
	 }
	
	 
	@GetMapping("/content_view") 
	public String content_view(BoardVO boardVO, Model model)throws Exception {
		
	   log.info("content_view");
	   model.addAttribute("content_view", service.get(boardVO.getbId()));
	   
	   List<ReplyVO> replyList = service.readReply(boardVO.getbId());
	   model.addAttribute("replyList", replyList);
	   
	   List<Map<String, Object>> fileList = service.selectFileList(boardVO.getbId());
		model.addAttribute("file", fileList);
	   
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
	public String write(BoardVO boardVO, MultipartHttpServletRequest mpRequest) throws Exception {
		
		log.info("write");
		service.writeBoard(boardVO, mpRequest);

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
	
	
	
	
//	//��� ����
//	@RequestMapping("/replyDelete")
//	public String replyDelete(ReplyVO vo)  {
//		
//		log.info("replyDelete...");
//		
//		service.deleteReply(vo);
//		
//		return "redirect:contentView";
//	}

	
}



