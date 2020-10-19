package com.wmk.ex.mapper;

import java.util.List;

import com.wmk.ex.page.Criteria;
import com.wmk.ex.service.FreeBoardServiceImpl;
import com.wmk.ex.vo.CommentVO;
import com.wmk.ex.vo.FreeBoardVO;
import com.wmk.ex.vo.FreeReplyVO;

public interface FreeBoardMapper {
	
	public List<FreeBoardVO> getList();

	//�Խ��� ��ȣ get
	public FreeBoardVO getNum(int fBoard_Num);
	
	//FBoardVO fid = UserVO id >> get id
	public FreeBoardVO getfId(String fId);
	
	//�Խ��� �ۼ�
	public void writeBoard(FreeBoardVO fboardVO);
	
	//�Խ��� ����
	public void updateModify(FreeBoardVO fboardVO);
	
	//�Խ��� ����
	public void deleteBoard(FreeBoardVO fboardVO);
	
	//��ȸ��
	public void addUphit(int fBoard_Num);
	
	//����¡ ó��
	public List<FreeBoardVO> getListWithPaging(Criteria cri);
	public int getTotalCount(Criteria cri);
	

	//��ǰ ��� �ۼ�
	public void registReply(FreeReplyVO reply) throws Exception;
	
	//��� ����Ʈ
	public List<FreeReplyVO> replyList(int fBoard_Num) throws Exception;
	
	//��� ����
	public void deleteReply(FreeReplyVO reply) throws Exception;
	
	//��� ���̵� üũ
	public String replyUserIdCheck(int repNum) throws Exception;
	
	//�Խ��� ���̵� üũ
	public String boardUserIdCheck(int fBoard_Num) throws Exception;
	
	
	
	
}
