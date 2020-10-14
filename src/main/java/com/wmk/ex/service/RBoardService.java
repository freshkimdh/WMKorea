package com.wmk.ex.service;


import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wmk.ex.vo.RBoardVO;



public interface RBoardService {
	
	//게시판 목록
	public List<RBoardVO> getReviewList(RBoardVO rboardVO);
	public List<RBoardVO> getReviewListAjax(RBoardVO rboardVO);
	
	//게시판 get rBoardNum
	public RBoardVO getrBoardNum(int rBoardNum);
	
	//RBoardVO rid = UserVO id >> get id
	public RBoardVO getrId(String rId);
	
	//게시판 작성
	public void rWriteBoard(RBoardVO rboardVO);
	//public void rWriteBoard(RBoardVO rboardVO, MultipartHttpServletRequest mpRequest);
	
	//게시판 수정
	public void updaterModify(RBoardVO rboardVO);
	
	//게시판 삭제
	public void deleterBoard(int rBoardNum);
	/*
	//페이징 처리
	public List<FBoardVO> getListWithPaging(Criteria cri);
	public int getTotalCount(Criteria cri);
*/

}
