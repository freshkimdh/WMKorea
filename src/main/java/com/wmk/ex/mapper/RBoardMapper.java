package com.wmk.ex.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wmk.ex.page.Criteria;
import com.wmk.ex.vo.FBoardVO;
import com.wmk.ex.vo.FReplyVO;
import com.wmk.ex.vo.RBoardVO;
import com.wmk.ex.vo.RReplyVO;


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
	
	//��� ���
	public List<RReplyVO> replyList(int rBoardNum) throws Exception;
	
	//��� �ۼ�
	public void registReply(RReplyVO reply) throws Exception;
	
	//��� ����
	public void deleteReply(RReplyVO reply) throws Exception;
	
	//���̵� üũ
	public String replyUserIdCheck(int repNum) throws Exception;
	public List<FBoardVO> getListWithPaging(Criteria cri);
	public int getTotalCount(Criteria cri);
	// 좋아요 버튼
	@Update("update REVIEW_BOARD set LIKE_CNT = LIKE_CNT + 1 where RBOARDNUM = #{RBOARDNUM}")
	public int updateLike(int rBoardNum);
	@Insert("insert into likeTo(likeNo, RBOARDNUM, id) values(like_to_seq.nextval, #{RBOARDNUM},#{id})")
	public int insertLike(@Param("RBOARDNUM")int rBoardNum,@Param("id")String id);
	// 좋아요 버튼 해제
	@Update("update REVIEW_BOARD set LIKE_CNT = LIKE_CNt -1 where RBOARDNUM = #{RBOARDNUM}")
	public int updateUnLike (int rBoardNum);
	@Delete("Delete from likeTo where id = #{id} and RBOARDNUM = #{RBOARDNUM}")
	public int deleteLike(@Param("RBOARDNUM") int rBoardNum, @Param("id") String id);
	
	
	@Select("select count(*) from likeTo where id = #{id} and RBOARDNUM = #{RBOARDNUM}")
	public int getCountLike(@Param("RBOARDNUM")int rBoardNum,@Param("id")String id);
	
	@Select("select Like_CNT from REVIEW_BOARD where RBOARDNUM = #{RBOARDNUM}")
	public void cntLike(int rBoardNum);
	
}
