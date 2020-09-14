package com.wmk.ex.service;

import java.util.List;

import org.apache.ibatis.annotations.Delete;

import com.wmk.ex.vo.BoardVO;


public interface BoardService {
	
	//@addUphit("update mvc_board set bHit = bHit + 1 where bId = #{bId}")
	public List<BoardVO> getList();

	public BoardVO get(int bno);

	@Delete("Delete from mvc_board where bid = #{bno}")
	public void remove(int bno);

	public void writeBoard(BoardVO boardVO);

	public void writeReply(BoardVO boardVO);

	public void modify(BoardVO boardVO);
	

	
	
	

	
	
}
