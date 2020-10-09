package com.wmk.ex.service;


import java.util.List;

import com.wmk.ex.vo.FBoardVO;



public interface FBoardService {
	
	//게시판 목록
	public List<FBoardVO> getList();
	
	//게시판 get num
	public FBoardVO getNum(int fBoard_Num);
	
	//FBoardVO fid = UserVO id >> get id
	public FBoardVO getfId(String fId);
	
	//게시판 작성
	public void writeBoard(FBoardVO fboardVO);
	
	//게시판 수정
	public void updateModify(FBoardVO fboardVO);
	
	//게시판 삭제
	public void deleteBoard(int fBoard_Num);
}
