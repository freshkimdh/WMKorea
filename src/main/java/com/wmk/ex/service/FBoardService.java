package com.wmk.ex.service;


import java.util.List;

import com.wmk.ex.vo.FBoardVO;



public interface FBoardService {
	
	//�Խ��� ���
	public List<FBoardVO> getList();
	
	//�ۼ��� get fId
	public FBoardVO getNum(int fBoard_Num);
	
	//�Խ��� �ۼ�
	public void writeBoard(FBoardVO fboardVO);
	
}
