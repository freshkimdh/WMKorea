package com.wmk.ex.service;


import java.util.List;

import com.wmk.ex.page.Criteria;
import com.wmk.ex.vo.FBoardVO;
import com.wmk.ex.vo.FReplyVO;



public interface FBoardService {
	
	//占쌉쏙옙占쏙옙 占쏙옙占�
	public List<FBoardVO> getList();
	
	//占쌉쏙옙占쏙옙 get num
	public FBoardVO getNum(int fBoard_Num);
	
	//FBoardVO fid = UserVO id >> get id
	public FBoardVO getfId(String fId);
	
	//게시판 작성
	public void writeBoard(FBoardVO fboardVO);
	
	//게시판 수정
	public void updateModify(FBoardVO fboardVO);
	
	//게시판 삭제 
	public void deleteBoard(FBoardVO fboardVO);
	
	
	//占쏙옙占쏙옙징 처占쏙옙
	public List<FBoardVO> getListWithPaging(Criteria cri);
	public int getTotalCount(Criteria cri);
	
	
	//占쏙옙占� 占쌜쇽옙
	public void registReply(FReplyVO reply) throws Exception;
	
	//占쏙옙占� 占쏙옙占쏙옙트
	public List<FReplyVO> replyList(int fBoard_Num) throws Exception;
	
	//占쏙옙占� 占쏙옙占쏙옙
	public void deleteReply(FReplyVO reply) throws Exception;
	
	//댓글  id 체크
	public String replyUserIdCheck(int repNum) throws Exception;
	
	//게시판 id 체크
	public String boardUserIdCheck(int fBoard_Num) throws Exception;

}
