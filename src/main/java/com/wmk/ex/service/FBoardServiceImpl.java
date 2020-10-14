package com.wmk.ex.service;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.wmk.ex.mapper.FBoardMapper;
import com.wmk.ex.page.Criteria;
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



