package com.wmk.ex.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wmk.ex.mapper.BoardMapper;
import com.wmk.ex.page.Criteria;
import com.wmk.ex.vo.BoardVO;
import com.wmk.ex.vo.ReplyVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j 
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	private BoardMapper mapper; 
	
	
	public List<BoardVO> getList() {
		
		log.info("getList..."); 
		
		return mapper.getList();
	}

	
	@Override
	public BoardVO get(int bno) {
		
		log.info("get...");
		BoardVO boardVO = mapper.read(bno);
						mapper.addUphit(bno);
		
		return boardVO;	
	}

	
	@Override
	public void remove(int bno) {
		
		log.info("remove........");
		mapper.delete(bno);
	}

	
	@Override
	public void writeBoard(BoardVO boardVO) {
		
		log.info("write........");
		mapper.insertBoard(boardVO);
	}

	
	@Override
	public void writeReply(BoardVO boardVO) {
		
		log.info("writeReply...");	
		mapper.updateShape(boardVO);
		mapper.insertReply(boardVO);
		//mapper.updateShape(boardVO);
	}

	
	@Override
	public void modify(BoardVO boardVO) {
		
		log.info("modify...");
		mapper.updateModify(boardVO);	
	}
	
	
	//∞Àªˆ±‚¥…
	@Override
	public List<BoardVO> boardByTitle(BoardVO boardVO) {
		
		log.info("boardByTitle...");

		return mapper.findBoardByTitle(boardVO);
	}
	
	
	//∆‰¿Ã¬° √≥∏Æ
	@Override
	public int getTotal(Criteria cri) {
		
		log.info("get total count...");
		return mapper.getTotalCount(cri);
	}

	
	@Override
	public List<BoardVO> getList(Criteria criteria) {
		
		log.info("get List with criteria"  + criteria);
		return mapper.getListWithPaging(criteria);
	}
	
	
	//¥Ò±€ ∏Ò∑œ ±‚¥…
	@Override
	public List<ReplyVO> readReply(int bId) {
		
		log.info("readReply...");
		return mapper.readReply(bId);
	}
	
	
	//¥Ò±€ ¿€º∫ 
	@Override
	public void writeReply(ReplyVO vo) {
		
		log.info("writeReply...");
		mapper.writeReply(vo);
	}
	
	//Ajax ¥Ò±€ ¿€º∫
	@Override
	public void writeReply(HashMap<String, String> comment) {
		log.info("writeReply2...");
		mapper.writeReply(comment);
		
	}

	//Ajax ¥Ò±€ ∏Ò∑œ
	@Override
	public List<ReplyVO> readReply(ReplyVO replyVO) {
		
		return mapper.readReply(replyVO);
	}

	
//	//¥Ò±€ ªË¡¶
//	@Override
//	public void deleteReply(ReplyVO vo) {
//		
//		log.info("deleteReply...");
//		mapper.deleteReply(vo);
//	}

}



