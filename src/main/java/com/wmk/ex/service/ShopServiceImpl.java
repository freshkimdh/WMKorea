package com.wmk.ex.service;


import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.wmk.ex.dao.ShopDAO;
import com.wmk.ex.mapper.ShopMapper;
import com.wmk.ex.vo.BoardVO;
import com.wmk.ex.vo.CartListVO;
import com.wmk.ex.vo.CartVO;
import com.wmk.ex.vo.CommentListVO;
import com.wmk.ex.vo.CommentVO;
import com.wmk.ex.vo.GoodsViewVO;
import com.wmk.ex.vo.OrderDetailVO;
import com.wmk.ex.vo.OrderListVO;
import com.wmk.ex.vo.OrderVO;
import com.wmk.ex.vo.UserVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@AllArgsConstructor
@Log4j 
@Service
public class ShopServiceImpl implements ShopService {
	
	
	@Inject
	private ShopDAO dao;
	
	private ShopMapper shopmapper;

	@Override
	public List<GoodsViewVO> list(int cateCode, int level) throws Exception {
		 int cateCodeRef = 0;  // 카테고리 참조 코드. 없어도 무관
		 
		 if(level == 1) {  // lavel 1 = 1차 분류.
		  
		  cateCodeRef = cateCode;
		  return dao.list(cateCode, cateCodeRef);
		  // 두가지 모두 cateCode로 해도 무관
		  
		 } else {  // lavel 2 = 2차 분류
		  
		  return dao.list(cateCode);
		  
		 }
	}

	@Override
	public GoodsViewVO goodsView(int gdsNum) throws Exception {
		
		return dao.goodsView(gdsNum);
	}

	@Override
	public void registReply(CommentVO comment) throws Exception {
		dao.registReply(comment);
		
	}

	@Override
	public List<CommentListVO> commentList(int gdsNum) throws Exception {
		
		return dao.commentList(gdsNum);
	}

	@Override
	public void deleteReply(CommentVO comment) throws Exception {
		
		dao.deleteReply(comment);
		
	}

	@Override
	public String idCheck(int repNum) throws Exception {
		
		return dao.idCheck(repNum);
	}

	//카트담기
	@Override
	public void addCart(CartVO cart) throws Exception {
		
		dao.addCart(cart);
		
	}
	
	//장바구니 보기
	@Override
	public List<CartListVO> cartList(String username) throws Exception {
		
		return shopmapper.cartList(username);
	}
	
	@Override
	public void deleteCart(CartVO cart) throws Exception {
		
		shopmapper.deleteCart(cart);
		
	}

	//주문정보
	@Override
	public void orderInfo(OrderVO order) throws Exception {
		
		shopmapper.orderInfo(order);
		
	}

	//주문 상세정보
	@Override
	public void orderInfo_Details(OrderDetailVO orderDetail) throws Exception {

		shopmapper.orderInfo_Details(orderDetail);
		
	}

	@Override
	public void cartAllDelete(String userId) throws Exception {
		shopmapper.cartAllDelete(userId);
		
	}

	@Override
	public List<OrderVO> orderList(OrderVO order) throws Exception {
		
		return shopmapper.orderList(order);
	}

	@Override
	public List<OrderListVO> orderView(OrderVO order) throws Exception {
		
		return shopmapper.orderView(order);
	}


	
//	@Override
//	public UserVO userIdread(String id) {
//		
//		log.info("get id...");
//		UserVO userVO = shopmapper.userIdread(id);
//
//		
//		return userVO;	
//	}


	

	


}



