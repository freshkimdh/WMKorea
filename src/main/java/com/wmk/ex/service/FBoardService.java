package com.wmk.ex.service;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.RequestParam;

import com.wmk.ex.page.Criteria;
import com.wmk.ex.vo.FBoardVO;



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
	
	
	public int updateLike(int fBoard_Num);
	public int insertLike(int fBoard_Num,String id);
	public void updateInsertLike(int fBoard_Num,String id);
	public int updateUnLike(int fBoard_Num);
	public int deleteLike(int fBoard_Num,String id);
	public void deleteUnlike(int fBoard_Num,String id);
	public int getLikeCount(int fBoard_Num,String id);

}
