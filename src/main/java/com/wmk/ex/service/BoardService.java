package com.wmk.ex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wmk.ex.page.Criteria;
import com.wmk.ex.vo.BoardVO;
import com.wmk.ex.vo.ReplyVO;


public interface BoardService {
	
	//@addUphit("update mvc_board set bHit = bHit + 1 where bId = #{bId}")
	public List<BoardVO> getList();

	public BoardVO get(int bno);

	@Delete("Delete from mvc_board where bid = #{bno}")
	public void remove(int bno);

	public void writeBoard(BoardVO boardVO, MultipartHttpServletRequest mpRequest) throws Exception;

	public void writeReply(BoardVO boardVO);

	public void modify(BoardVO boardVO);
	
	//검색기능
	public List<BoardVO> boardByTitle(BoardVO boardVO);
	
	//페이징 처리
	public int getTotal(Criteria cri);
	public List<BoardVO> getList(Criteria criteria);
	
	//댓글 목록
	public List<ReplyVO> readReply(int bId);
	
	//댓글 작성
	public void writeReply(ReplyVO vo);
	
	//Ajax 댓글 작성
	public void writeReply(HashMap<String, String> comment);
	
	//Ajax 댓글 목록
	public List<ReplyVO> readReply(ReplyVO replyVO);
	
	//첨부파일 조회
	public List<Map<String, Object>> selectFileList(int bId) throws Exception;

	
//	//댓글 삭제
//	public void deleteReply(ReplyVO vo);
	
}
