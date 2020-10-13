package com.wmk.ex.service;


import java.util.List;

import com.wmk.ex.page.Criteria;
import com.wmk.ex.vo.FBoardVO;
import com.wmk.ex.vo.FReplyVO;



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
	
	//페이징 처리
	public List<FBoardVO> getListWithPaging(Criteria cri);
	public int getTotalCount(Criteria cri);
	
	
	//댓글 작성
	public void registReply(FReplyVO reply) throws Exception;
	
	//댓글 리스트
	public List<FReplyVO> replyList(int fBoard_Num) throws Exception;
	
	//댓글 삭제
	public void deleteReply(FReplyVO reply) throws Exception;
	
	//아이디 체크
	public String replyUserIdCheck(int repNum) throws Exception;

}
