package com.wmk.ex.mapper;

import java.util.List;

import com.wmk.ex.vo.CategoryVO;
import com.wmk.ex.vo.GoodsVO;
import com.wmk.ex.vo.GoodsViewVO;

public interface AdminMapper {
	
	//카테고리
	public List<CategoryVO> category() throws Exception; 
	
	//상품등록
	public void register(GoodsVO vo) throws Exception; 
	
	//상품 목록
	public List<GoodsViewVO> goodslist() throws Exception;
	
	//상품 조회
	public GoodsViewVO goodsView(int gdsNum) throws Exception;
	
	//상품 수정
	public void goodsModify(GoodsVO vo) throws Exception; 
	
	//상품 삭제
	public void goodsDelete(int gdsNum) throws Exception;
	
	
}
