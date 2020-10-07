package com.wmk.ex.service;


import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.wmk.ex.dao.ShopDAO;
import com.wmk.ex.mapper.ShopMapper;
import com.wmk.ex.vo.BoardVO;
import com.wmk.ex.vo.CommentVO;
import com.wmk.ex.vo.GoodsViewVO;
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



