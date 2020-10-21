package com.wmk.ex.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

import com.wmk.ex.vo.ReviewBoardVO;
import com.wmk.ex.vo.ReviewReplyVO;


public interface ReviewBoardMapper {
	
	//핫플레이스 게시판 목록
	public List<ReviewBoardVO> getHotList(ReviewBoardVO reviewBoardVO);
	
	//여행 게시판 목록
	public List<ReviewBoardVO> getReviewList(ReviewBoardVO reviewBoardVO);
	public List<ReviewBoardVO> getReviewListAjax(ReviewBoardVO reviewBoardVO);
	
	//여행 게시판 번호
	public ReviewBoardVO getrBoardNum(int reviewBoardNum);
	
	//여행 게시판 글작성
	public void rWriteBoard(ReviewBoardVO reviewBoardVO);
	
	//여행 게시판 수정
	public void updaterModify(ReviewBoardVO reviewBoardVO);
	
	//여행 게시판 삭제
	public void deleterBoard(int reviewBoardNum);
	
	//여행 게시판 조회수
	public void addUprHit(int reviewBoardNum);
	
	//업로드 파일
	public void insertFile(Map<String, Object> map);
	
	//업로드 파일 수정
	public void updateFile(Map<String, Object> map);
	
	//업로드 파일 삭제
	public void deleteFile(int reviewBoardNum);
	
	//업로드 파일 리스트로 뿌려주기
	public List<Map<String, Object>> selectFileList(int reviewBoardNum);
	
	public List<ReviewReplyVO> replyList(int reviewBoardNum) throws Exception;
	
	public void registReply(ReviewReplyVO reviewReply) throws Exception;
	
	public void deleteReply(ReviewReplyVO reviewReply) throws Exception;
	
	public String replyUserIdCheck(int repNum) throws Exception;

	//좋아요
	public int updateLike(int reviewBoardNum);
	public int insertLike(@Param("RBOARDNUM")int reviewBoardNum,@Param("id")String id);
	
	// 좋아요 버튼 해제
	public int updateUnLike (int reviewBoardNum);
	public int deleteLike(@Param("RBOARDNUM") int reviewBoardNum, @Param("id") String id);
	
	//좋아요 
	public int getCountLike(@Param("RBOARDNUM")int reviewBoardNum,@Param("id")String id);
	
	public int getLikeCnt(int reviewBoardNum);
	
	public void cntLike(int reviewBoardNum);
}
