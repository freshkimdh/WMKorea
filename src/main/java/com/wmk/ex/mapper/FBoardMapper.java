package com.wmk.ex.mapper;

import java.util.List;
import com.wmk.ex.vo.FBoardVO;


public interface FBoardMapper {
	
	public List<FBoardVO> getList();

	//�Խ��� ��ȣ get
	public FBoardVO getNum(int fBoard_Num);
	
	//�Խ��� �ۼ�
	public void writeBoard(FBoardVO fboardVO);
	


	
}
