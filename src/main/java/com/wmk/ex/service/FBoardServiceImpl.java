package com.wmk.ex.service;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.wmk.ex.mapper.FBoardMapper;
import com.wmk.ex.page.Criteria;
import com.wmk.ex.vo.FBoardVO;
import com.wmk.ex.vo.FReplyVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j 
@Service
@AllArgsConstructor
public class FBoardServiceImpl implements FBoardService {
	
	private FBoardMapper mapper; 
	
	//ï¿½Ô½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½
	@Override
	public List<FBoardVO> getList() {
		log.info("getList..."); 
		
		return mapper.getList();
	}
	
	//ï¿½Ô½ï¿½ï¿½ï¿½ ï¿½ï¿½È£ get
	@Override
	public FBoardVO getNum(int fBoard_Num) {
		FBoardVO fboardVO = mapper.getNum(fBoard_Num);
							mapper.addUphit(fBoard_Num);
		
		log.info("getNum..."); 
		
		return fboardVO;
	}
	
	//FBoardVO fid = UserVO id >> get id
	@Override
	public FBoardVO getfId(String fId) {
		FBoardVO fboardVO = mapper.getfId(fId);
							
		return fboardVO;
	}	
	
	
	//ï¿½Ô½ï¿½ï¿½ï¿½ ï¿½Û¼ï¿½
	@Override
	public void writeBoard(FBoardVO fboardVO) {
		
		log.info("writeBoard........");
		
		mapper.writeBoard(fboardVO);		
	}
	
	//ï¿½Ô½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
	@Override
	public void updateModify(FBoardVO fboardVO) {
		
		mapper.updateModify(fboardVO);	
	}
	
	//ï¿½Ô½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
	@Override
	public void deleteBoard(int fBoard_Num) {
		
		mapper.deleteBoard(fBoard_Num);		
	}
	
	//ï¿½ï¿½ï¿½ï¿½Â¡ Ã³ï¿½ï¿½
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
	
	
	//´ñ±Û ÀÛ¼º
	@Override
	public void registReply(FReplyVO reply) throws Exception {
		mapper.registReply(reply);
		
	}
	
	//´ñ±Û ¸ñ·Ï
	@Override
	public List<FReplyVO> replyList(int fBoard_Num) throws Exception {
		
		log.info("get replyList...");
		
		return mapper.replyList(fBoard_Num);
	}

	@Override
	public void deleteReply(FReplyVO reply) throws Exception {
		
		log.info("deleteReply...");
		
		mapper.deleteReply(reply);
		
	}

	@Override
	public String replyUserIdCheck(int repNum) throws Exception {
		
		log.info("idCheck...");
		
		return mapper.replyUserIdCheck(repNum);
	}
	

	@Override
	public int updateLike(int fBoard_Num) {
		// TODO Auto-generated method stub
		return mapper.updateLike(fBoard_Num);
	}

	@Override
	public int insertLike(int fBoard_Num, String id) {
		// TODO Auto-generated method stub
		return mapper.insertLike(fBoard_Num, id);
	}

	@Transactional
	public void updateInsertLike(int fBoard_Num,String id) {
//		log.info("updateLike 	:"  + mapper.updateLike(fBoard_Num));
//		log.info("insertLike	:"  + mapper.insertLike(fBoard_Num, id));
		mapper.updateLike(fBoard_Num);
		mapper.insertLike(fBoard_Num, id);
	}

	@Override
	public int updateUnLike(int fBoard_Num) {
		// TODO Auto-generated method stub
		return mapper.updateUnLike(fBoard_Num);
	}
	@Override
	public int deleteLike(int fBoard_Num, String id) {
		// TODO Auto-generated method stub
		return mapper.deleteLike(fBoard_Num, id);
	}
	
	@Transactional
	public void deleteUnlike(int FBOARD_NUM,String id) {
		mapper.updateUnLike(FBOARD_NUM);
		mapper.deleteLike(FBOARD_NUM, id);
	}

	@Override
	public int getLikeCount(int fBoard_Num, String id) {
		return mapper.getCountLike(fBoard_Num, id);
	}

	@Override
	public void cntLike(int fBoard_Num) {
		// TODO Auto-generated method stub
		mapper.cntLike(fBoard_Num);
		
	}
	

	
	

}



