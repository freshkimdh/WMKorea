package com.wmk.ex.service;


import java.util.List;
import org.springframework.stereotype.Service;
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
	
	//占쌉쏙옙占쏙옙 占쏙옙占�
	@Override
	public List<FBoardVO> getList() {
		log.info("getList..."); 
		
		return mapper.getList();
	}
	
	//게시판 번호 get
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
	
	
	//占쌉쏙옙占쏙옙 占쌜쇽옙
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
	public void deleteBoard(FBoardVO fboardVO) {
		
		mapper.deleteBoard(fboardVO);	
		
	}
	
	//占쏙옙占쏙옙징 처占쏙옙
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
	
	
	//占쏙옙占� 占쌜쇽옙
	@Override
	public void registReply(FReplyVO reply) throws Exception {
		mapper.registReply(reply);
		
	}
	
	//占쏙옙占� 占쏙옙占�
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
		log.info("replyUserIdCheck " + repNum);
		log.info("replyUserIdCheck: " + mapper.replyUserIdCheck(repNum));
		
		return mapper.replyUserIdCheck(repNum);
	}
	
	//게시판 id 체크
	@Override
	public String boardUserIdCheck(int fBoard_Num) throws Exception {
		
		log.info("board idCheck...");
		log.info(mapper.boardUserIdCheck(fBoard_Num));
		log.info(fBoard_Num);
		
		//log.info(mapper.getNum(fId));
		
		return mapper.boardUserIdCheck(fBoard_Num);
	}
	



	
	

}



