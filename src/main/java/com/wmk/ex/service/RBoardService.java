package com.wmk.ex.service;


import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wmk.ex.page.Criteria;
import com.wmk.ex.vo.FBoardVO;
import com.wmk.ex.vo.RBoardVO;
import com.wmk.ex.vo.RReplyVO;



public interface RBoardService {
	
	//�Խ��� ���
	
	public List<RBoardVO> getReviewList(RBoardVO rboardVO);
	public List<RBoardVO> getReviewListAjax(RBoardVO rboardVO);
	
	//�Խ��� get rBoardNum
	public RBoardVO getrBoardNum(int rBoardNum);
	
	//RBoardVO rid = UserVO id >> get id
	public RBoardVO getrId(String rId);
	
	//�Խ��� �ۼ�
	public void rWriteBoard(RBoardVO rboardVO);
	//public void rWriteBoard(RBoardVO rboardVO, MultipartHttpServletRequest mpRequest);
	
	//�Խ��� ����
	public void updaterModify(RBoardVO rboardVO);
	
	//�Խ��� ����
	public void deleterBoard(int rBoardNum);
	/*
	//����¡ ó��
	public List<FBoardVO> getListWithPaging(Criteria cri);
	public int getTotalCount(Criteria cri);
*/
	
	//��� ���
	public List<RReplyVO> replyList(int rBoardNum) throws Exception;
	
	//��� �ۼ�
	public void registReply(RReplyVO reply) throws Exception;
		
	//��� ����
	public void deleteReply(RReplyVO reply) throws Exception;
	
	//���̵� üũ
	public String replyUserIdCheck(int repNum) throws Exception;
	
	//����¡ ó��
	public List<FBoardVO> getListWithPaging(Criteria cri);
	public int getTotalCount(Criteria cri);
		
		
	public int updateLike(int rBoardNum);
	public int insertLike(int rBoardNum,String id);
	public void updateInsertLike(int rBoardNum,String id);
	public int updateUnLike(int rBoardNum);
	public int deleteLike(int rBoardNum,String id);
	public void deleteUnlike(int rBoardNum,String id);
	public int getLikeCount(int rBoardNum,String id);
	public void cntLike(int rBoardNum);
		
	

}
