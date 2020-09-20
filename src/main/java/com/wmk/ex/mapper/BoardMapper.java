package com.wmk.ex.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import com.wmk.ex.page.Criteria;
import com.wmk.ex.vo.BoardVO;


public interface BoardMapper {
	
	//getList() �̰Ŷ� xml�� id�� �Ȱ��ƾ� �Ѵ�. (�Լ���)
	//"List<BoardVO>" �̰Ŷ� boardMapper.xml�� resultType="com.wmk.ex.mapper.BoardVO" �̰Ŷ� ��ġ (���׸��κ�) 
	public List<BoardVO> getList();

	public BoardVO read(int bno);
	
	@Delete("Delete from wmk_mvc_board where bid = #{bno}")
	public void delete(int bno);

	public void insertBoard(BoardVO boardvo);
	
	public void updateShape(BoardVO boardVO);

	public void insertReply(BoardVO boardVO);

	public void updateModify(BoardVO boardVO);
	
	public void addUphit(int bno);
	
	//�˻����
	public List<BoardVO> findBoardByTitle(BoardVO boardVO);
		
	//����¡ ó��
	public List<BoardVO> getListWithPaging(Criteria cri);
	public int getTotalCount(Criteria cri);

	
}
