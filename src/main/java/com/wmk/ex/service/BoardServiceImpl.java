package com.wmk.ex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wmk.ex.mapper.BoardMapper;
import com.wmk.ex.page.Criteria;
import com.wmk.ex.util.FileUtils;
import com.wmk.ex.vo.BoardVO;
import com.wmk.ex.vo.LikeVO;
import com.wmk.ex.vo.ReplyVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j 
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	private BoardMapper mapper; 
	
	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
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
	public void writeBoard(BoardVO boardVO, MultipartHttpServletRequest mpRequest) throws Exception {
		
		log.info("write........");
		
		mapper.insertBoard(boardVO);
		
		List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(boardVO, mpRequest); 
		
		System.out.println("listlist" + list);
		
		int size = list.size();
		for(int i=0; i<size; i++){ 
			mapper.insertFile(list.get(i)); 
			
			System.out.println("list.get(i)" + list.get(i));
		}
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
	public List<ReplyVO> readReply(int bId) {
		
		log.info("readReply...");
		return mapper.readReply(bId);
	}
	
	
	//댓글 작성 
	@Override
	public void writeReply(ReplyVO vo) {
		
		log.info("writeReply...");
		mapper.writeReply(vo);
	}
	
	//Ajax 댓글 작성
	@Override
	public void writeReply(HashMap<String, String> comment) {
		log.info("writeReply2...");
		mapper.writeReply(comment);
		
	}

	//Ajax 댓글 목록
	@Override
	public List<ReplyVO> readReply(ReplyVO replyVO) {
		
		return mapper.readReply(replyVO);
	}
	
	// 첨부파일 조회
	@Override
	public List<Map<String, Object>> selectFileList(int bId) {
		return mapper.selectFileList(bId);
	}
	
	// 좋아요 활성화
	@Override
	public void insertLike(Map<Integer, String> map) {
		mapper.insertLike(map);
	}
	
	// 좋아요 취소
	@Override
	public void deleteLike(Map<Integer, String> map) {
		mapper.deleteLike(map);
	}
	
	//좋아요 여부 확인
	@Override
	public LikeVO getLike(LikeVO likeVO) {
		likeVO = mapper.getLike(likeVO);
		
		return likeVO;
	}
	
	//좋아요 카운트
	@Override
	public int likeCnt(int bId) {
		
		return mapper.likeCnt(bId);
	}
	
	
	
	

	
//	//댓글 삭제
//	@Override
//	public void deleteReply(ReplyVO vo) {
//		
//		log.info("deleteReply...");
//		mapper.deleteReply(vo);
//	}

}



