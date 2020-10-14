package com.wmk.ex.mapper;

import java.util.List;

import com.wmk.ex.vo.CartListVO;
import com.wmk.ex.vo.CartVO;
import com.wmk.ex.vo.CategoryVO;
import com.wmk.ex.vo.GoodsVO;
import com.wmk.ex.vo.GoodsViewVO;
import com.wmk.ex.vo.UserVO;

public interface ShopMapper {
	
//	//카테고리별 상품 리스트
//	public List<GoodsViewVO> list(int cateCode) throws Exception;
//	
//	//상품조회
//	public GoodsViewVO goodslist(int gdsNum) throws Exception;
	

	public UserVO userIdread(String id) throws Exception;
	
	//장바구니 보기
	public List<CartListVO> cartList(String username);
	
	//장바구니 삭제
	public void deleteCart(CartVO cart) throws Exception;

}
