package com.wmk.ex.service;


import java.util.List;

import com.wmk.ex.vo.FBoardVO;



public interface FBoardService {
	
	//게시판 목록
	public List<FBoardVO> getList();
	
	//작성자 get fId
	public FBoardVO getNum(int fBoard_Num);
	
	//게시판 작성
	public void writeBoard(FBoardVO fboardVO);
	
}
