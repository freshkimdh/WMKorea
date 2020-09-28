package com.wmk.ex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wmk.ex.page.Criteria;
import com.wmk.ex.vo.BoardVO;
import com.wmk.ex.vo.ReplyVO;


public interface BoardService {
	
	//@addUphit("update mvc_board set bHit = bHit + 1 where bId = #{bId}")
	public List<BoardVO> getList();

	public BoardVO get(int bno);

	@Delete("Delete from mvc_board where bid = #{bno}")
	public void remove(int bno);

	public void writeBoard(BoardVO boardVO, MultipartHttpServletRequest mpRequest) throws Exception;

	public void writeReply(BoardVO boardVO);

	public void modify(BoardVO boardVO);
	
	//�˻����
	public List<BoardVO> boardByTitle(BoardVO boardVO);
	
	//����¡ ó��
	public int getTotal(Criteria cri);
	public List<BoardVO> getList(Criteria criteria);
	
	//��� ���
	public List<ReplyVO> readReply(int bId);
	
	//��� �ۼ�
	public void writeReply(ReplyVO vo);
	
	//Ajax ��� �ۼ�
	public void writeReply(HashMap<String, String> comment);
	
	//Ajax ��� ���
	public List<ReplyVO> readReply(ReplyVO replyVO);
	
	//÷������ ��ȸ
	public List<Map<String, Object>> selectFileList(int bId) throws Exception;

	
//	//��� ����
//	public void deleteReply(ReplyVO vo);
	
}
