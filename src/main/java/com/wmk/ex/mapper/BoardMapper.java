package com.wmk.ex.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;

import com.wmk.ex.page.Criteria;
import com.wmk.ex.vo.BoardVO;
import com.wmk.ex.vo.LikeVO;
import com.wmk.ex.vo.ReplyVO;

public interface BoardMapper {
	
	//getList() 이거랑 xml의 id랑 똑같아야 한다. (함수명)
	//"List<BoardVO>" 이거랑 boardMapper.xml의 resultType="com.wmk.ex.mapper.BoardVO" 이거랑 일치 (제네릭부분) 
	public List<BoardVO> getList();

	public BoardVO read(int bno);
	
	@Delete("Delete from wmk_mvc_board where bid = #{bno}")
	public void delete(int bno);

	public void insertBoard(BoardVO boardvo);
	
	public void updateShape(BoardVO boardVO);

	public void insertReply(BoardVO boardVO);

	public void updateModify(BoardVO boardVO);
	
	public void addUphit(int bno);
	
	//검색기능
	public List<BoardVO> findBoardByTitle(BoardVO boardVO);
		
	//페이징 처리
	public List<BoardVO> getListWithPaging(Criteria cri);
	public int getTotalCount(Criteria cri);
	
	//댓글 목록 기능
	public List<ReplyVO> readReply(int bId);
	
	//댓글 작성
	public void writeReply(ReplyVO vo);
	
	//Ajax 댓글 작성
	public void writeReply(HashMap<String, String> comment);
	
	//Ajax 댓글 목록
	public List<ReplyVO> readReply(ReplyVO replyVO);
	
	//첨부파일 업로드
	public void insertFile(Map<String, Object> map); 
		
	//첨부파일 조회
	public List<Map<String, Object>> selectFileList(int bId);

	
	//좋아요 데이터
	public void insertLike(Map<Integer, String> map); 
	
	// 좋아요 취소
	public void deleteLike(Map<Integer, String> map);
	
	//좋아요 여부
	public LikeVO getLike(LikeVO likeVO);

	//좋아요 카운트
	public int likeCnt(int bId);
		
	
//	//댓글 삭제
//	public void deleteReply(ReplyVO vo);

	
}
