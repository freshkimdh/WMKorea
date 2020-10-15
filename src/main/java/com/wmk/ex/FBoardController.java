package com.wmk.ex;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wmk.ex.page.Criteria;
import com.wmk.ex.page.PageDTO;
import com.wmk.ex.service.FBoardService;
import com.wmk.ex.vo.CommentListVO;
import com.wmk.ex.vo.FBoardVO;
import com.wmk.ex.vo.FReplyVO;
import com.wmk.ex.vo.UserVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@AllArgsConstructor
@Log4j
public class FBoardController {
	
	private FBoardService service;
	
	//占쌉쏙옙占쏙옙 占쏙옙占�
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
	
	//占쌉쏙옙占쏙옙 占쏙옙占쏙옙
	@RequestMapping(value = "/free_contentView", method = RequestMethod.GET)
	public String contentView(FBoardVO fboardVO, Model model, int fBoard_Num) throws Exception {
		
	   log.info("content_view...");
	   model.addAttribute("contentView", service.getNum(fboardVO.getfBoard_Num()));
	   model.addAttribute("list", service.getList());
	   
//	   List<FReplyVO> reply = service.replyList(fBoard_Num);
//	   model.addAttribute("reply", reply);
//	   log.info("reply...");
	   
	   return "/free_board/contentView";
	   
	  
	}
	
	//占쌉쏙옙占쏙옙 占쌜쇽옙 view
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
	
	//占쌉쏙옙占쏙옙 占쏙옙占쏙옙
	@GetMapping("/free_modifyView") 
	public String modifyView(FBoardVO fboardVO, Model model) {
	
		log.info("modifyView...");	
		model.addAttribute("modifyView", service.getNum(fboardVO.getfBoard_Num()));
		
		return "/free_board/modifyView";
	}
	
	//占쌉쏙옙占쏙옙 占쏙옙占쏙옙
	@RequestMapping("/free_modify")
	public String modify(FBoardVO fboardVO) {
		
		log.info("free_modify...");
		service.updateModify(fboardVO);
		
		return "redirect:free_boardList";
	}
	
	//게시판 삭제
	//@ResponseBody
	@GetMapping("/free_delete") 
	public String delete(FBoardVO fboardVO) throws Exception {
		log.info("free_delete...");
		
		int result = 0;
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 
		String username = ((UserDetails)principal).getUsername();
		log.info("username:" + username);
		String userId = service.boardUserIdCheck(fboardVO.getfBoard_Num());
		log.info("userid:" + userId);
		
		if(username.equals(userId)) {
			 
			 fboardVO.setfId(username); 
			 service.deleteBoard(fboardVO);
			  
			 result = 1;
			 
			 log.info("삭제 성공");
			 
		 }
		
	   
		return "redirect:free_boardList";

	}
	
	//댓글 등록
	@ResponseBody
	@RequestMapping(value = "/free_contentView/registReply", method = RequestMethod.POST)
	public void registReply(FReplyVO reply) throws Exception {
	 log.info("regist reply...");
	 
	 Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	 if (principal instanceof UserDetails) { // user id 가져오기 성공
	   String username = ((UserDetails)principal).getUsername();
	   log.info("유저 id " + username);
	   reply.setId(username);
	   
	 } else { //user id 가져오기 실패
	   String username = principal.toString();
	   log.info("유저 id 가져오기 실패");
	   
	 }
	 
	 service.registReply(reply);
	 
	 log.info("댓글 등록  메소드 실행 완료");
	 
	}
	
	
	//댓글 목록
	@ResponseBody
	@RequestMapping("/free_contentView/replyList")
	public List<FReplyVO> getReplyList(@RequestParam("n") int fBoard_Num) throws Exception {
	 log.info("get reply list");
	    
	 List<FReplyVO> reply = service.replyList(fBoard_Num);
	  
	 return reply;
	}
	
	
	//댓글 삭제
	@ResponseBody
	@RequestMapping(value = "/free_contentView/deleteReply", method = RequestMethod.POST)
	public String getReplyList(FReplyVO reply) throws Exception {
	 log.info("post delete reply");

	 int result = 0;
	 
	 Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	 
	 String username = ((UserDetails)principal).getUsername();
	 log.info("username id " + username);
	 String userId = service.replyUserIdCheck(reply.getRepNum());
	 log.info("userId id " + userId);
	 
	 if(username.equals(userId)) {
			 
		 reply.setId(username); 
		 service.deleteReply(reply);
		  
		 result = 1;
	 }
	 
	 return String.valueOf(result);
	 
	}
	
	
	
	
}



