package com.wmk.ex;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wmk.ex.page.Criteria;
import com.wmk.ex.page.PageDTO;
import com.wmk.ex.service.BoardService;
import com.wmk.ex.vo.BoardVO;
import com.wmk.ex.vo.LikeVO;
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
	
	
	/*
	 * //검색기능
	 * 
	 * @RequestMapping("/boardByTitle") public String boardByTitle(BoardVO boardVO,
	 * Model model) { //무엇이든(제목이든, 이름이던...등등) 출력하고자 봉투를 인자로 넣음.
	 * 
	 * System.out.println(boardVO.toString()); //BoardVO.java 에 있는 toString()을 출력하기
	 * 위해.(test용) log.info("boardByTitle");
	 * 
	 * service.boardByTitle(boardVO); //유저가 입력한 것을 서비스에서 받아주기위함.( 유저가 입력한 제목을 서비스로
	 * 넘기기 위함) System.out.println(service.boardByTitle(boardVO));
	 * 
	 * model.addAttribute("boardByTitle", service.boardByTitle(boardVO));
	 * 
	 * return "list"; }
	 */
	
	//게시판 좋아요 활성화
		@ResponseBody
		@RequestMapping(value = "boardLike", method = RequestMethod.POST)
		public String boardLike(@RequestParam Map<Integer, String> map, HttpSession session) {	
			System.out.println("하트 눌러!");
			String str = "success";
			
			service.insertLike(map);
			
			return str;
		}
		
		//게시판 좋아요 취소
		@ResponseBody
		@RequestMapping(value = "deleteLike", method = RequestMethod.POST)
		public String deleteLike(@RequestParam Map<Integer, String> map) {
			
			System.out.println("하트 취소!");
			
			service.deleteLike(map);		
			
			return "success";
		}
		
		//게시판 좋아요	여부 확인
		@ResponseBody
		@RequestMapping(value = "getLike", method = RequestMethod.POST)
		public LikeVO getLike(@RequestParam int bId, String id){
			LikeVO likeVO = new LikeVO();
			likeVO.setbId(bId);
			likeVO.setId(id);
				
			likeVO = service.getLike(likeVO);
			int bLikeNumber = likeVO.getbLikeNumber();
				
			return likeVO;
		}
		
		//게시판 좋아요	여부 확인
		@ResponseBody
		@RequestMapping(value = "likeCnt", method = RequestMethod.POST)
		public int likeCnt(@RequestParam int bId){
			int a = service.likeCnt(bId);
		
			return a;
		}
	
	
	
//	//댓글 삭제
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



