package com.wmk.ex.service;


import java.util.List;

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
}
