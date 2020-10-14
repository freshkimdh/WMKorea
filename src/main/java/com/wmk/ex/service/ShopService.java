package com.wmk.ex.service;

import java.util.List;

import com.wmk.ex.vo.CartListVO;
import com.wmk.ex.vo.CartVO;
import com.wmk.ex.vo.CategoryVO;
import com.wmk.ex.vo.CommentListVO;
import com.wmk.ex.vo.CommentVO;
import com.wmk.ex.vo.GoodsVO;
import com.wmk.ex.vo.GoodsViewVO;
import com.wmk.ex.vo.OrderDetailVO;
import com.wmk.ex.vo.OrderListVO;
import com.wmk.ex.vo.OrderVO;
import com.wmk.ex.vo.UserVO;



public interface ShopService {
	
	//카테고리별 상품 리스트
	public List<GoodsViewVO> list(int cateCode, int level) throws Exception;
	
	//상품 조회
	public GoodsViewVO goodsView(int gdsNum) throws Exception;
	
	//상품 댓글 작성
	public void registReply(CommentVO comment) throws Exception;
	
	//상품 소감(댓글) 리스트
	public List<CommentListVO> commentList(int gdsNum) throws Exception;
	
	//상품 소감(댓글) 삭제
	public void deleteReply(CommentVO comment) throws Exception;
	
	//아이디 체크
	public String idCheck(int repNum) throws Exception;
	
	//장바구니 담기
	public void addCart(CartVO cart) throws Exception;
	
	//장바구니 보기
	public List<CartListVO> cartList(String username) throws Exception;
	
	//장바구니 삭제
	public void deleteCart (CartVO cart) throws Exception;
	
	
	// 주문정보
	public void orderInfo(OrderVO order) throws Exception;

	//주문 상세 정보
	public void orderInfo_Details(OrderDetailVO orderDetail) throws Exception;
	
	//카트비우기 
	public void cartAllDelete(String userId) throws Exception;
	
	//특정 유저의 주문 목록보기
	public List<OrderVO> orderList(OrderVO order) throws Exception;
	
	//특정 큰 주문의 상세내용 보기
	public List<OrderListVO> orderView(OrderVO order) throws Exception;
	

}
