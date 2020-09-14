package com.wmk.ex.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import com.wmk.ex.vo.BoardVO;


public interface BoardMapper {
	
	//getList() 이거랑 xml의 id랑 똑같아야 한다. (함수명)
	//"List<BoardVO>" 이거랑 boardMapper.xml의 resultType="com.wmk.ex.mapper.BoardVO" 이거랑 일치 (제네릭부분) 
	public List<BoardVO> getList();

	public BoardVO read(int bno);
	
	@Delete("Delete from mvc_board where bid = #{bno}")
	public void delete(int bno);

	public void insertBoard(BoardVO boardvo);
	
	public void updateShape(BoardVO boardVO);

	public void insertReply(BoardVO boardVO);

	public void updateModify(BoardVO boardVO);
	
	public void addUphit(int bno);

}
