package com.wmk.ex.dao;

import java.util.List;

import com.wmk.ex.vo.GoodsViewVO;


public interface ShopDAO {

	// 카테고리별 상품 리스트: 1차
	public List<GoodsViewVO> list(int cateCode, int cateCodeRef) throws Exception;

	// 카테고리별 상품 리스트: 2차
	public List<GoodsViewVO> list(int cateCode) throws Exception;
	
	//상품조회
	public GoodsViewVO goodsView(int gdsNum) throws Exception;
	
		
}  