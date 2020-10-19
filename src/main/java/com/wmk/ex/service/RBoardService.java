package com.wmk.ex.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wmk.ex.page.Criteria;
import com.wmk.ex.vo.FreeBoardVO;
import com.wmk.ex.vo.RBoardVO;
import com.wmk.ex.vo.RReplyVO;



public interface RBoardService {
	

	//list, list ajax
	public List<RBoardVO> getReviewList(RBoardVO rboardVO);
	public List<RBoardVO> getReviewListAjax(RBoardVO rboardVO);
	
	
	public RBoardVO getrBoardNum(int rBoardNum);
	
	//RBoardVO rid = UserVO id >> get id
	public RBoardVO getrId(String rId);
	
	//글작성
	public void rWriteBoard(RBoardVO rboardVO, MultipartHttpServletRequest mpRequest) throws Exception;
	
	//수정
	public void updaterModify(RBoardVO rboardVO, MultipartHttpServletRequest mpRequest) throws Exception;

	//삭제
	public void deleterBoard(int rBoardNum);
	
	//업로드 파일 list로 뿌려주기
	public List<Map<String, Object>> selectFileList(int rBoardNum) throws Exception;
	
	//업로드된 파일 삭제
	public void removerBoard(int fileSize);
	
	public List<RReplyVO> replyList(int rBoardNum) throws Exception;
	
	public void registReply(RReplyVO reply) throws Exception;
		
	public void deleteReply(RReplyVO reply) throws Exception;
	
	public String replyUserIdCheck(int repNum) throws Exception;
	

	//좋아요 기능		
	public int updateLike(int rBoardNum);
	public int insertLike(int rBoardNum,String id);
	public void updateInsertLike(int rBoardNum,String id);
	public int updateUnLike(int rBoardNum);
	public int deleteLike(int rBoardNum,String id);
	public void deleteUnlike(int rBoardNum,String id);
	public int getLikeCount(int rBoardNum,String id);
	public void cntLike(int rBoardNum);
		
	

}
