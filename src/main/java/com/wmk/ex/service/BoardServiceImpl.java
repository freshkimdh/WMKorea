package com.wmk.ex.service;

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
	
	
	//검색기능
	@Override
	public List<BoardVO> boardByTitle(BoardVO boardVO) {
		
		log.info("boardByTitle...");

		return mapper.findBoardByTitle(boardVO);
	}
	
	
	//페이징 처리
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
	
	
	//댓글 목록 기능
	@Override
	public List<ReplyVO> readReply(int bid) {
		
		log.info("readReply...");
		return mapper.readReply(bid);
	}
	
	
	//댓글 작성 
	@Override
	public void writeReply(ReplyVO vo) {
		
		log.info("writeReply...");
		mapper.writeReply(vo);
	}

	
//	//댓글 삭제
//	@Override
//	public void deleteReply(ReplyVO vo) {
//		
//		log.info("deleteReply...");
//		mapper.deleteReply(vo);
//	}

}



