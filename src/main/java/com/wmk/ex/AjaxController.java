package com.wmk.ex;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wmk.ex.page.Criteria;
import com.wmk.ex.page.PageDTO;
import com.wmk.ex.service.BoardService;
import com.wmk.ex.vo.BoardVO;
import com.wmk.ex.vo.ReplyVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
//test시윤
@Controller
@AllArgsConstructor
@Log4j
public class AjaxController {
	
	private BoardService service;
	
	//��� �ۼ�
	@PostMapping("/comment")
	@ResponseBody
	public List<ReplyVO>  comment(@RequestBody HashMap<String, String> comment) {
		
		System.out.println(comment.toString());
		System.out.println(comment.get("bId"));
		
		//��� �ۼ�
		service.writeReply(comment);	
		
		
		//��� ���
		ReplyVO replyVO = new ReplyVO();
		
		replyVO.setbId(Integer.parseInt(comment.get("bId")));
		
		List<ReplyVO> replylist = service.readReply(replyVO);	
		
		return replylist;
		
		
	}
	
	
}



