package com.wmk.ex.mapper;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wmk.ex.vo.RBoardVO;


public interface RBoardMapper {
	
	public List<RBoardVO> getReviewList(RBoardVO rboardVO);
	public List<RBoardVO> getReviewListAjax(RBoardVO rboardVO);

	//�Խ��� ��ȣ get
	public RBoardVO getrBoardNum(int rBoardNum);
	
	//RBoardVO rid = UserVO id >> get id
	public RBoardVO getrId(String rId);
	
	//�Խ��� �ۼ�
	public void rWriteBoard(RBoardVO rboardVO);
//	public void rWriteBoard(RBoardVO rboardVO , MultipartHttpServletRequest mpRequest);
	
	//�Խ��� ����
	public void updaterModify(RBoardVO rboardVO);
	
	//�Խ��� ����
	public void deleterBoard(int rBoardNum);
	
	//��ȸ��
	public void addUprHit(int rBoardNum);
	
	/*
	//����¡ ó��
	public List<FBoardVO> getListWithPaging(Criteria cri);
	public int getTotalCount(Criteria cri);
	*/
}
