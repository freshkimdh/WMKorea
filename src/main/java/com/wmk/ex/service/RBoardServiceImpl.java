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
	
	//�Խ��� ���
	public List<RBoardVO> getReviewList(RBoardVO rboardVO) {
		log.info("getrList..."); 
		
		return rmapper.getReviewList(rboardVO);
	}
	//�Խ��� ���
	public List<RBoardVO> getReviewListAjax(RBoardVO rboardVO) {
		log.info("getrList..."); 
		
		return rmapper.getReviewListAjax(rboardVO);
	}
	
	//�Խ��� ��ȣ get
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
	
	//�Խ��� �ۼ�
	@Override
	public void rWriteBoard(RBoardVO rboardVO) {
//		public void rWriteBoard(RBoardVO rboardVO, MultipartHttpServletRequest mpRequest) {
		
		log.info("rWriteBoard........");
		
		rmapper.rWriteBoard(rboardVO);
		//rmapper.rWriteBoard(rboardVO, mpRequest);
	}	
	
	
	
	//�Խ��� ����
	@Override
	public void updaterModify(RBoardVO rboardVO) {
		
		rmapper.updaterModify(rboardVO);	
	}
	
	//�Խ��� ����
	@Override
	public void deleterBoard(int rBoardNum) {
		
		rmapper.deleterBoard(rBoardNum);		
	}
	/*
	//����¡ ó��
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



