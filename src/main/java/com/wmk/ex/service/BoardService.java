package com.wmk.ex.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Delete;

import com.wmk.ex.page.Criteria;
import com.wmk.ex.vo.BoardVO;
import com.wmk.ex.vo.ReplyVO;


public interface BoardService {
	
	//@addUphit("update mvc_board set bHit = bHit + 1 where bId = #{bId}")
	public List<BoardVO> getList();

	public BoardVO get(int bno);

	@Delete("Delete from mvc_board where bid = #{bno}")
	public void remove(int bno);

	public void writeBoard(BoardVO boardVO);

	public void writeReply(BoardVO boardVO);

	public void modify(BoardVO boardVO);
	
	//°Ë»ö±â´É
	public List<BoardVO> boardByTitle(BoardVO boardVO);
	
	//ÆäÀÌÂ¡ Ã³¸®
	public int getTotal(Criteria cri);
	public List<BoardVO> getList(Criteria criteria);
	
	//´ñ±Û ¸ñ·Ï
	public List<ReplyVO> readReply(int bId);
	
	//´ñ±Û ÀÛ¼º
	public void writeReply(ReplyVO vo);
	
	//Ajax ´ñ±Û ÀÛ¼º
	public void writeReply(HashMap<String, String> comment);
	
	//Ajax ´ñ±Û ¸ñ·Ï
	public List<ReplyVO> readReply(ReplyVO replyVO);
	
//	//´ñ±Û »èÁ¦
//	public void deleteReply(ReplyVO vo);
	
}
