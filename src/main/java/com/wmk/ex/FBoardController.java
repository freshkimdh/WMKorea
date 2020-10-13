package com.wmk.ex;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wmk.ex.page.Criteria;
import com.wmk.ex.page.PageDTO;
import com.wmk.ex.service.FBoardService;
import com.wmk.ex.vo.CustomUser;
import com.wmk.ex.vo.FBoardVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@AllArgsConstructor
@Log4j
public class FBoardController {
	
	private FBoardService service;
	
	//�Խ��� ���
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
	
	//�Խ��� ����
	@GetMapping("/free_contentView") 
	public String contentView(FBoardVO fboardVO, Model model, Authentication authentication) {
		
		CustomUser loginInfo =  authentication != null ? (CustomUser) authentication.getPrincipal() : null;
		if(loginInfo == null) {
			// 로그인 안된사람은 좋아요 눌르지 못하니 false리턴
			model.addAttribute("isSelectLike", false);
		} else {
			// 로그인 유저가 해당 게시글 좋아요 버튼 눌렀는지 알기 위해 해당 테이블 조회
			int likeCount = service.getLikeCount(fboardVO.getfBoard_Num(), loginInfo.getUser().getId());
			model.addAttribute("isSelectLike", likeCount > 0);

		}
        
	   log.info("content_view...");
	   model.addAttribute("contentView", service.getNum(fboardVO.getfBoard_Num()));
	   model.addAttribute("list", service.getList());
	   
	   return "/free_board/contentView";
	}
	
	//�Խ��� �ۼ� view
	@RequestMapping("/free_writeView")
	public String writeView() {
		
		log.info("free_writeView...");
		
		return "/free_board/writeView";
	}
	
	//�Խ��� �ۼ� 
	@RequestMapping("/free_write")
	public String write(FBoardVO fboardVO) throws Exception {
		
		log.info("free_write...");
		service.writeBoard(fboardVO);

		return "redirect:free_boardList";
	}
	
	//�Խ��� ����
	@GetMapping("/free_modifyView") 
	public String modifyView(FBoardVO fboardVO, Model model) {
	
		log.info("modifyView...");	
		model.addAttribute("modifyView", service.getNum(fboardVO.getfBoard_Num()));
		
		return "/free_board/modifyView";
	}
	
	//�Խ��� ����
	@RequestMapping("/free_modify")
	public String modify(FBoardVO fboardVO) {
		
		log.info("free_modify...");
		service.updateModify(fboardVO);
		
		return "redirect:free_boardList";
	}
	
	//�Խ��� ����
	@GetMapping("/free_delete") 
	public String delete(FBoardVO fboardVO) {
		
	    log.info("free_delete...");
	    service.deleteBoard(fboardVO.getfBoard_Num());
	   
	    return "redirect:free_boardList";
	}
}



