package com.wmk.ex.service;

import java.util.List;

import com.wmk.ex.vo.CartListVO;
import com.wmk.ex.vo.CartVO;
import com.wmk.ex.vo.CategoryVO;
import com.wmk.ex.vo.CommentListVO;
import com.wmk.ex.vo.CommentVO;
import com.wmk.ex.vo.GoodsVO;
import com.wmk.ex.vo.GoodsViewVO;
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

}
