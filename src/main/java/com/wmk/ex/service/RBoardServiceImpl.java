package com.wmk.ex.service;


import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wmk.ex.mapper.RBoardMapper;
import com.wmk.ex.page.Criteria;
import com.wmk.ex.vo.FBoardVO;
import com.wmk.ex.vo.RBoardVO;
import com.wmk.ex.vo.RReplyVO;

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
	
	
	//��� ���
	@Override
	public List<RReplyVO> replyList(int rBoardNum) throws Exception {
		
		log.info("get replyList...");
		
		return rmapper.replyList(rBoardNum);
	}
	
	//��� �ۼ�
	@Override
	public void registReply(RReplyVO reply) throws Exception {
		
		rmapper.registReply(reply);
		
	}
	
	//��� ����
	@Override
	public void deleteReply(RReplyVO reply) throws Exception {
		log.info("deleteReply...");
		
		rmapper.deleteReply(reply);
		
	}
	
	//��� ���̵� Ȯ��
	@Override
	public String replyUserIdCheck(int repNum) throws Exception {
		log.info("idCheck...");
		
		return rmapper.replyUserIdCheck(repNum);
	}
	
	//����¡ ó��
		@Override
		public int getTotalCount(Criteria cri) {
			
			log.info("get total count...");
			return rmapper.getTotalCount(cri);
		}

		
		@Override
		public List<FBoardVO> getListWithPaging(Criteria criteria) {
			
			log.info("get List with criteria"  + criteria);
			return rmapper.getListWithPaging(criteria);
		}

		@Override
		public int updateLike(int rBoardNum) {
			// TODO Auto-generated method stub
			return rmapper.updateLike(rBoardNum);
		}

		@Override
		public int insertLike(int rBoardNum, String id) {
			// TODO Auto-generated method stub
			return rmapper.insertLike(rBoardNum, id);
		}

		@Transactional
		public void updateInsertLike(int rBoardNum,String id) {
			rmapper.updateLike(rBoardNum);
			rmapper.insertLike(rBoardNum, id);
		}

		@Override
		public int updateUnLike(int rBoardNum) {
			// TODO Auto-generated method stub
			return rmapper.updateUnLike(rBoardNum);
		}
		@Override
		public int deleteLike(int rBoardNum, String id) {
			// TODO Auto-generated method stub
			return rmapper.deleteLike(rBoardNum, id);
		}
		
		@Transactional
		public void deleteUnlike(int rBoardNum,String id) {
			rmapper.updateUnLike(rBoardNum);
			rmapper.deleteLike(rBoardNum, id);
		}

		@Override
		public int getLikeCount(int rBoardNum, String id) {
			return rmapper.getCountLike(rBoardNum, id);
		}

		@Override
		public void cntLike(int rBoardNum) {
			// TODO Auto-generated method stub
			rmapper.cntLike(rBoardNum);
			
		}
		



	
	

}



