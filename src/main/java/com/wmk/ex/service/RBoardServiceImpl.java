package com.wmk.ex.service;


import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wmk.ex.mapper.RBoardMapper;
import com.wmk.ex.page.Criteria;
import com.wmk.ex.vo.RBoardVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j 
@Service
@AllArgsConstructor
public class RBoardServiceImpl implements RBoardService {
	
	private RBoardMapper rmapper; 
	
	//게시판 목록
	public List<RBoardVO> getReviewList(RBoardVO rboardVO) {
		log.info("getrList..."); 
		
		return rmapper.getReviewList(rboardVO);
	}
	//게시판 목록
	public List<RBoardVO> getReviewListAjax(RBoardVO rboardVO) {
		log.info("getrList..."); 
		
		return rmapper.getReviewListAjax(rboardVO);
	}
	
	//게시판 번호 get
	@Override
	public RBoardVO getrBoardNum(int rBoardNum) {
		RBoardVO rboardVO = rmapper.getrBoardNum(rBoardNum);
							rmapper.addUprHit(rBoardNum);
		
		log.info("getrBoardNum..."); 
		
		return rboardVO;
	}
	
	//RBoardVO rid = UserVO id >> get id
	@Override
	public RBoardVO getrId(String rId) {
		RBoardVO rboardVO = rmapper.getrId(rId);
							
		return rboardVO;
	}
	
	//게시판 작성
	@Override
	public void rWriteBoard(RBoardVO rboardVO) {
//		public void rWriteBoard(RBoardVO rboardVO, MultipartHttpServletRequest mpRequest) {
		
		log.info("rWriteBoard........");
		
		rmapper.rWriteBoard(rboardVO);
		//rmapper.rWriteBoard(rboardVO, mpRequest);
	}	
	
	
	
	//게시판 수정
	@Override
	public void updaterModify(RBoardVO rboardVO) {
		
		rmapper.updaterModify(rboardVO);	
	}
	
	//게시판 삭제
	@Override
	public void deleterBoard(int rBoardNum) {
		
		rmapper.deleterBoard(rBoardNum);		
	}
	/*
	//페이징 처리
	@Override
	public int getTotalCount(Criteria cri) {
		
		log.info("get total count...");
		return mapper.getTotalCount(cri);
	}

	
	@Override
	public List<FBoardVO> getListWithPaging(Criteria criteria) {
		
		log.info("get List with criteria"  + criteria);
		return mapper.getListWithPaging(criteria);
	}



	
	*/

}



