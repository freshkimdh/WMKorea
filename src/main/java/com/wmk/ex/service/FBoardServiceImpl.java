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
	
	//게시판 목록
	@Override
	public List<FBoardVO> getList() {
		log.info("getList..."); 
		
		return mapper.getList();
	}
	
	//게시판 번호 get
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
	
	
	//게시판 작성
	@Override
	public void writeBoard(FBoardVO fboardVO) {
		
		log.info("writeBoard........");
		
		mapper.writeBoard(fboardVO);		
	}
	
	//게시판 수정
	@Override
	public void updateModify(FBoardVO fboardVO) {
		
		mapper.updateModify(fboardVO);	
	}
	
	//게시판 삭제
	@Override
	public void deleteBoard(int fBoard_Num) {
		
		mapper.deleteBoard(fBoard_Num);		
	}



	
	

}



