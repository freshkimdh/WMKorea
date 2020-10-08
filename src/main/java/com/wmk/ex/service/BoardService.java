package com.wmk.ex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wmk.ex.page.Criteria;
import com.wmk.ex.vo.BoardVO;
import com.wmk.ex.vo.LikeVO;
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
	
	//占싯삼옙占쏙옙占�
	public List<BoardVO> boardByTitle(BoardVO boardVO);
	
	//占쏙옙占쏙옙징 처占쏙옙
	public int getTotal(Criteria cri);
	public List<BoardVO> getList(Criteria criteria);
	
	//占쏙옙占� 占쏙옙占�
	public List<ReplyVO> readReply(int bId);
	
	//占쏙옙占� 占쌜쇽옙
	public void writeReply(ReplyVO vo);
	
	//Ajax 占쏙옙占� 占쌜쇽옙
	public void writeReply(HashMap<String, String> comment);
	
	//Ajax 占쏙옙占� 占쏙옙占�
	public List<ReplyVO> readReply(ReplyVO replyVO);
	
	//첨占쏙옙占쏙옙占쏙옙 占쏙옙회
	public List<Map<String, Object>> selectFileList(int bId) throws Exception;

	//좋아요 데이터 추가
	public void insertLike(Map<Integer, String> map);
	
	//좋아요 데이터 삭제
	public void deleteLike(Map<Integer, String> map);
	
	//좋아요 여부 확인
	public LikeVO getLike(LikeVO likeVO);

	//좋아요 카운트
	public int likeCnt(int bId);
	
//	//占쏙옙占� 占쏙옙占쏙옙
//	public void deleteReply(ReplyVO vo);
	
}
