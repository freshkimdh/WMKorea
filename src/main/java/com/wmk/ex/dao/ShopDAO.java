package com.wmk.ex.dao;

import java.util.List;

import com.wmk.ex.vo.CommentVO;
import com.wmk.ex.vo.CartVO;
import com.wmk.ex.vo.CommentListVO;
import com.wmk.ex.vo.GoodsViewVO;
import com.wmk.ex.vo.UserVO;


public interface ShopDAO {

	// 카테고리별 상품 리스트: 1차
	public List<GoodsViewVO> list(int cateCode, int cateCodeRef) throws Exception;

	// 카테고리별 상품 리스트: 2차
	public List<GoodsViewVO> list(int cateCode) throws Exception;
	
	//상품조회
	public GoodsViewVO goodsView(int gdsNum) throws Exception;
	
	//상품 댓글 작성
	public void registReply(CommentVO comment) throws Exception;
	
	//상품 소감(댓글) 리스트
	public List<CommentListVO> commentList(int gdsNum) throws Exception;
	
	//상품 소감(댓글) 삭제
	public void deleteReply(CommentVO comment) throws Exception;
	
	//아이디 체크
	public String idCheck(int repNum) throws Exception;
	
	//카트 담기 (정경채)
	public void addCart(CartVO cart) throws Exception;
	
	
	
		
}  