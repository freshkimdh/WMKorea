package com.wmk.ex.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.RequestParam;

import com.wmk.ex.page.Criteria;
import com.wmk.ex.vo.CommentVO;
import com.wmk.ex.vo.FBoardVO;
import com.wmk.ex.vo.FReplyVO;

import lombok.Delegate;


public interface FBoardMapper {
	
	public List<FBoardVO> getList();

	//�Խ��� ��ȣ get
	public FBoardVO getNum(int fBoard_Num);
	
	//FBoardVO fid = UserVO id >> get id
	public FBoardVO getfId(String fId);
	
	//�Խ��� �ۼ�
	public void writeBoard(FBoardVO fboardVO);
	
	//�Խ��� ����
	public void updateModify(FBoardVO fboardVO);
	
	//�Խ��� ����
	public void deleteBoard(int fBoard_Num);
	
	//��ȸ��
	public void addUphit(int fBoard_Num);
	
	//����¡ ó��
	public List<FBoardVO> getListWithPaging(Criteria cri);
	public int getTotalCount(Criteria cri);
	


	//��ǰ ��� �ۼ�
	public void registReply(FReplyVO reply) throws Exception;
	
	//��� ����Ʈ
	public List<FReplyVO> replyList(int fBoard_Num) throws Exception;
	
	//��� ����
	public void deleteReply(FReplyVO reply) throws Exception;
	
	//���̵� üũ
	public String replyUserIdCheck(int repNum) throws Exception;
	

	@Update("update FREE_BOARD set LIKE_CNT = LIKE_CNT + 1 where fBoard_Num = #{fBoard_Num}")
	public int updateLike(int fBoard_Num);
	@Insert("insert into likeTo(likeNo, fBoard_Num, id) values(like_to_seq.nextval, #{fBoard_Num},#{id})")
	public int insertLike(@Param("fBoard_Num")int fBoard_Num,@Param("id")String id);
	
	@Select("select count(*) from likeTo where id = #{id} and fBoard_Num = #{fBoard_Num}")
	public int getCountLike(@Param("fBoard_Num")int fBoard_Num,@Param("id")String id);
	
	
	@Update("update FREE_BOARD set LIKE_CNT = 0 where fBoard_Num = #{fBoard_Num}")
	public int updateUnLike (int fBoard_Num);
	@Delete("Delete from likeTo where id = #{id} and fBoard_Num = #{fBoard_Num}")
	public int deleteLike(@Param("fBoard_Num") int fBoard_Num, @Param("id") String id);
	
	@Select("select Like_CNT from FREE_BOARD where fBoard_Num = #{fBoard_Num}")
	public void cntLike(int fBoard_Num);

}
