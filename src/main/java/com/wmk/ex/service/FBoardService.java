package com.wmk.ex.service;


import java.util.List;

import com.wmk.ex.page.Criteria;
import com.wmk.ex.vo.FBoardVO;
import com.wmk.ex.vo.FReplyVO;



public interface FBoardService {
	
	//�Խ��� ���
	public List<FBoardVO> getList();
	
	//�Խ��� get num
	public FBoardVO getNum(int fBoard_Num);
	
	//FBoardVO fid = UserVO id >> get id
	public FBoardVO getfId(String fId);
	
	//�Խ��� �ۼ�
	public void writeBoard(FBoardVO fboardVO);
	
	//�Խ��� ����
	public void updateModify(FBoardVO fboardVO);
	
	//�Խ��� ����
	public void deleteBoard(int fBoard_Num);
	
	//����¡ ó��
	public List<FBoardVO> getListWithPaging(Criteria cri);
	public int getTotalCount(Criteria cri);
	
	
	//��� �ۼ�
	public void registReply(FReplyVO reply) throws Exception;
	
	//��� ����Ʈ
	public List<FReplyVO> replyList(int fBoard_Num) throws Exception;
	
	//��� ����
	public void deleteReply(FReplyVO reply) throws Exception;
	
	//���̵� üũ
	public String replyUserIdCheck(int repNum) throws Exception;

}
