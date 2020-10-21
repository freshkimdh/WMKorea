package com.wmk.ex.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wmk.ex.page.Criteria;
import com.wmk.ex.vo.FreeBoardVO;
import com.wmk.ex.vo.RBoardVO;
import com.wmk.ex.vo.RReplyVO;


public interface RBoardMapper {
	
	public List<RBoardVO> getrList(RBoardVO rboardVO);
	public List<RBoardVO> getReviewList(RBoardVO rboardVO);
	public List<RBoardVO> getReviewListAjax(RBoardVO rboardVO);

	
	public RBoardVO getrBoardNum(int rBoardNum);
	
	
	//RBoardVO rid = UserVO id >> get id
	public RBoardVO getrId(String rId);
	
	
	public void rWriteBoard(RBoardVO rboardVO);
	
	
	public void updaterModify(RBoardVO rboardVO);
	
	
	public void deleterBoard(int rBoardNum);
	
	
	public void addUprHit(int rBoardNum);
	
	//업로드 파일
	public void insertFile(Map<String, Object> map);
	
	//업로드 파일 수정
	public void updateFile(Map<String, Object> map);
	
	//업로드 파일 삭제
	public void deleteFile(int rBoardNum);
	
	//업로드 파일 리스트로 뿌려주기
	public List<Map<String, Object>> selectFileList(int rBoardNum);
	
	
	public List<RReplyVO> replyList(int rBoardNum) throws Exception;
	
	
	public void registReply(RReplyVO reply) throws Exception;
	
	
	public void deleteReply(RReplyVO reply) throws Exception;
	
	
	public String replyUserIdCheck(int repNum) throws Exception;

	//좋아요
	public int updateLike(int rBoardNum);
	
	public int insertLike(@Param("RBOARDNUM")int rBoardNum,@Param("id")String id);
	
	// 좋아요 버튼 해제
	
	public int updateUnLike (int rBoardNum);
	
	public int deleteLike(@Param("RBOARDNUM") int rBoardNum, @Param("id") String id);
	
	
	public int getCountLike(@Param("RBOARDNUM")int rBoardNum,@Param("id")String id);
	
	public int getLikeCnt(int rBoardNum);
	
	public void cntLike(int rBoardNum);
}
