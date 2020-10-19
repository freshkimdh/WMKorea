package com.wmk.ex.service;


import java.util.List;

import com.wmk.ex.page.Criteria;
import com.wmk.ex.vo.FreeBoardVO;
import com.wmk.ex.vo.FreeReplyVO;



public interface FreeBoardService {
	
	//占쌉쏙옙占쏙옙 占쏙옙占�
	public List<FreeBoardVO> getList();
	
	//占쌉쏙옙占쏙옙 get num
	public FreeBoardVO getNum(int fBoard_Num);
	
	//FBoardVO fid = UserVO id >> get id
	public FreeBoardVO getfId(String fId);
	
	//게시판 작성
	public void writeBoard(FreeBoardVO fboardVO);
	
	//게시판 수정
	public void updateModify(FreeBoardVO fboardVO);
	
	//게시판 삭제 
	public void deleteBoard(FreeBoardVO fboardVO);
	
	
	//占쏙옙占쏙옙징 처占쏙옙
	public List<FreeBoardVO> getListWithPaging(Criteria cri);
	public int getTotalCount(Criteria cri);
	
	
	//占쏙옙占� 占쌜쇽옙
	public void registReply(FreeReplyVO reply) throws Exception;
	
	//占쏙옙占� 占쏙옙占쏙옙트
	public List<FreeReplyVO> replyList(int fBoard_Num) throws Exception;
	
	//占쏙옙占� 占쏙옙占쏙옙
	public void deleteReply(FreeReplyVO reply) throws Exception;
	
	//댓글  id 체크
	public String replyUserIdCheck(int repNum) throws Exception;
	
	//게시판 id 체크
	public String boardUserIdCheck(int fBoard_Num) throws Exception;

}
