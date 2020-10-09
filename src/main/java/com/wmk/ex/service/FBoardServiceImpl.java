package com.wmk.ex.service;


import java.util.List;
import org.springframework.stereotype.Service;
import com.wmk.ex.mapper.FBoardMapper;
import com.wmk.ex.vo.BoardVO;
import com.wmk.ex.vo.FBoardVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j 
@Service
@AllArgsConstructor
public class FBoardServiceImpl implements FBoardService {
	
	private FBoardMapper mapper; 
	
	//�Խ��� ���
	@Override
	public List<FBoardVO> getList() {
		log.info("getList..."); 
		
		return mapper.getList();
	}
	
	//�Խ��� ��ȣ get
	@Override
	public FBoardVO getNum(int fBoard_Num) {
		FBoardVO fboardVO = mapper.getNum(fBoard_Num);
		log.info("getNum..."); 
		
		return fboardVO;
	}
	
	//FBoardVO fid = UserVO id >> get id
	@Override
	public FBoardVO getfId(String fId) {
		FBoardVO fboardVO = mapper.getfId(fId);
		return fboardVO;
	}	
	
	
	//�Խ��� �ۼ�
	@Override
	public void writeBoard(FBoardVO fboardVO) {
		
		log.info("writeBoard........");
		
		mapper.writeBoard(fboardVO);		
	}
	
	//�Խ��� ����
	@Override
	public void updateModify(FBoardVO fboardVO) {
		
		mapper.updateModify(fboardVO);	
	}
	
	//�Խ��� ����
	@Override
	public void deleteBoard(int fBoard_Num) {
		
		mapper.deleteBoard(fBoard_Num);		
	}



	
	

}



