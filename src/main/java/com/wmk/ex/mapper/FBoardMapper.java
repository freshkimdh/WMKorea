package com.wmk.ex.mapper;

import java.util.List;
import com.wmk.ex.vo.FBoardVO;


public interface FBoardMapper {
	
	public List<FBoardVO> getList();

	//게시판 번호 get
	public FBoardVO getNum(int fBoard_Num);
	
	//게시판 작성
	public void writeBoard(FBoardVO fboardVO);
	


	
}
