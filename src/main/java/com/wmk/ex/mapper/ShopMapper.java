package com.wmk.ex.mapper;

import java.util.List;

import com.wmk.ex.vo.CartListVO;
import com.wmk.ex.vo.CartVO;
import com.wmk.ex.vo.CategoryVO;
import com.wmk.ex.vo.GoodsVO;
import com.wmk.ex.vo.GoodsViewVO;
import com.wmk.ex.vo.OrderDetailVO;
import com.wmk.ex.vo.OrderListVO;
import com.wmk.ex.vo.OrderVO;
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
	
	//주문정보
	public void orderInfo(OrderVO order) throws Exception;
		
	//주문 상세정보
	public void orderInfo_Details(OrderDetailVO orderDetail) throws Exception;
	
	//주문시 카트 비우기
	public void cartAllDelete(String userId) throws Exception;
	
	//특정 유저의 주문목록 보기
	public List<OrderVO> orderList(OrderVO order) throws Exception;
	
	//특정 큰 주문의 상세내용 보기
	public List<OrderListVO> orderView(OrderVO order) throws Exception;
	

}
