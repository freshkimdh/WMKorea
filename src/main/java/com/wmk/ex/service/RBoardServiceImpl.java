package com.wmk.ex.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wmk.ex.mapper.RBoardMapper;
import com.wmk.ex.page.Criteria;
import com.wmk.ex.util.FileUtils;
import com.wmk.ex.vo.FreeBoardVO;
import com.wmk.ex.vo.RBoardVO;
import com.wmk.ex.vo.RReplyVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j 
@Service
@AllArgsConstructor
public class RBoardServiceImpl implements RBoardService {
	
	private RBoardMapper rmapper; 
	
	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
	public List<RBoardVO> getrList(RBoardVO rboardVO){
		log.info("Get rlist");
		return rmapper.getrList(rboardVO);
	}
	
	public List<RBoardVO> getReviewList(RBoardVO rboardVO) {
		log.info("getrList..."); 
		
		log.info("imple"+rmapper.getReviewList(rboardVO));
		
		return rmapper.getReviewList(rboardVO);
	}
	public List<RBoardVO> getReviewListAjax(RBoardVO rboardVO) {
		log.info("getrList..."); 
		
		return rmapper.getReviewListAjax(rboardVO);
	}
	
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
	
	@SuppressWarnings("null")
	@Override
	public void rWriteBoard(RBoardVO rboardVO, MultipartHttpServletRequest mpRequest) throws Exception{
		
		log.info("rWriteBoard........");
		
		rmapper.rWriteBoard(rboardVO);
		
		List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(rboardVO, mpRequest); 
		
		System.out.println("abcd" + list);
		System.out.println("abcd" + list.size());
		
		
		int size = list.size();
		Map<String, Object> map = new HashMap<>();

		
		System.out.println("abcdmap" + map);
		if(size == 0) {
			System.out.println("�־�Ÿ��");
			map.put("ORIGINAL_FILE_NAME", " ");
			map.put("STORED_FILE_NAME", " ");
			map.put("FILE_SIZE", 0);
			rmapper.insertFile(map);
		}else {
			for(int i=0; i<size; i++){ 
				rmapper.insertFile(list.get(i));
				System.out.println("list.get(i)" + list.get(i));
			}
		}
	}	
	
	
	
	@Override
	public void updaterModify(RBoardVO rboardVO, MultipartHttpServletRequest mpRequest) throws Exception{
		
		log.info("아아아아아=" + rboardVO);
		rmapper.updaterModify(rboardVO);
		
		List<Map<String, Object>> list = fileUtils.parseInsertFileInfo(rboardVO, mpRequest);
		log.info("list=" + list);
		int size = list.size();
		for(int i=0; i<size; i++){ 
			rmapper.updateFile(list.get(i)); 
			System.out.println("list.get(i)" + list.get(i));
		}
	}
	
	@Override
	public void deleterBoard(int rBoardNum) {
		
		rmapper.deleterBoard(rBoardNum);		
	}
	
	@Override
	public List<Map<String, Object>> selectFileList(int rBoardNum) throws Exception {

		return rmapper.selectFileList(rBoardNum);
	}
	
	
	@Override
	public void removerBoard(int rBoardNum) {
		
		rmapper.deleteFile(rBoardNum);
	}
	
	
	@Override
	public List<RReplyVO> replyList(int rBoardNum) throws Exception {
		
		log.info("get replyList...");
		
		return rmapper.replyList(rBoardNum);
	}
	
	@Override
	public void registReply(RReplyVO reply) throws Exception {
		
		rmapper.registReply(reply);
		
	}
	
	@Override
	public void deleteReply(RReplyVO reply) throws Exception {
		log.info("deleteReply...");
		
		rmapper.deleteReply(reply);
		
	}
	
	@Override
	public String replyUserIdCheck(int repNum) throws Exception {
		log.info("idCheck...");
		
		return rmapper.replyUserIdCheck(repNum);
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



