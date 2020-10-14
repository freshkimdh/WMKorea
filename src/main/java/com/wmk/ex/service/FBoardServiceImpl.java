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
	
	
	//��� �ۼ�
	@Override
	public void registReply(FReplyVO reply) throws Exception {
		mapper.registReply(reply);
		
	}
	
	//��� ���
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
	



	
	

}



