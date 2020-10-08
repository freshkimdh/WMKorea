package com.wmk.ex;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wmk.ex.service.BoardService;
import com.wmk.ex.vo.BoardVO;
import com.wmk.ex.vo.ReplyVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@AllArgsConstructor
@Log4j
public class BoardController {
	
	private BoardService service;
	
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
	public String write2(BoardVO boardVO, MultipartHttpServletRequest mpRequest) throws Exception {
		
		log.info("write2");
		service.writeBoard(boardVO, mpRequest);
		
		return "redirect:boardList";
	}
	
	
	@GetMapping("/contentView") 
	public String contentView(BoardVO boardVO, Model model) {
		
	   log.info("content_view");
	   model.addAttribute("contentView", service.get(boardVO.getbId()));
	   model.addAttribute("list", service.getList());
	   
	   List<ReplyVO> replyList = service.readReply(boardVO.getbId());
	   model.addAttribute("replyList", replyList);
	   
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
	
	//댓글작성
	@RequestMapping("/replyWrite")
	public String replyWrite(ReplyVO vo) {
		
		log.info("replyWrite..");
	
		service.writeReply(vo);
		
		return "redirect:contentView";
	}
	
	//검색기능 
	@RequestMapping("/boardByTitle")
	public String boardByTitle(BoardVO boardVO, Model model) {  
		
		System.out.println(boardVO.toString());  
		log.info("boardByTitle");
		
		service.boardByTitle(boardVO); 
		System.out.println(service.boardByTitle(boardVO));
		
		model.addAttribute("boardByTitle", service.boardByTitle(boardVO));
		
		return "boardList";
	}
	
	
	///////////////////////////////////////////////좋아요 기능
/*		
	//게시판 좋아요 활성화
	@ResponseBody
	@RequestMapping(value = "boardLike", method = RequestMethod.POST)
	public String boardLike(@RequestParam Map<Integer, String> map, HttpSession session) {	
		
		String str = "success";
		
		service.insertLike(map);
	
		return str;
	}
	
	//게시판 좋아요 취소
	@ResponseBody
	@RequestMapping(value = "deleteLike", method = RequestMethod.POST)
	public String deleteLike(@RequestParam Map<Integer, String> map) {	
	service.deleteLike(map);		
	return "success";
	}
	
	//게시판 좋아요	여부 확인
	@ResponseBody
	@RequestMapping(value = "board/GetLike", method = RequestMethod.POST)
	public LikeVO getLike(@RequestParam int bId, String id){
	LikeVO likeVO = new LikeVO();
	likeVO.setbId(bId);
	likeVO.setid(id);
	
	likeDTO = boardService.getLike(likeDTO);
	int like_number = likeDTO.getLike_number();
	
	return likeDTO;
	}
	
	//게시판 좋아요	여부 확인
	@ResponseBody
	@RequestMapping(value = "board/LikeCnt", method = RequestMethod.POST)
	public int likeCnt(@RequestParam int b_number){
	int a = boardService.likeCnt(b_number);
	
	return a;
	}
	
	
	//////////////관리자 여행지게시판 페이지 (09/23: 정경채 추가)////////////
	
	@GetMapping("/seoulList") 
	public String seoulList(Model model) {
		
		log.info("seoulList...");
		return "/admin_travel_board/seoulList";

	}
	
	@GetMapping("/seoulCotentView") 
	public String seoulCotentView(Model model) {
		
		log.info("seoulList...");
		return "/admin_travel_board/seoulCotentView";

	}
	
	
	@RequestMapping("/travelWriteView")
	public String travelWriteView(BoardVO boardVO) {
		
		log.info("/travelWriteView");
		
		return "/admin_travel_board/travelWriteView";
		

	}
*/	
}



