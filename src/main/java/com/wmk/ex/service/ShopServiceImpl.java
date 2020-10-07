package com.wmk.ex.service;


import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.wmk.ex.dao.ShopDAO;


import com.wmk.ex.vo.GoodsViewVO;


import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@AllArgsConstructor
@Log4j 
@Service
public class ShopServiceImpl implements ShopService {
	
	
	@Inject
	private ShopDAO dao;

	@Override
	public List<GoodsViewVO> list(int cateCode, int level) throws Exception {
		 int cateCodeRef = 0;  // ī�װ� ���� �ڵ�. ��� ����
		 
		 if(level == 1) {  // lavel 1 = 1�� �з�.
		  
		  cateCodeRef = cateCode;
		  return dao.list(cateCode, cateCodeRef);
		  // �ΰ��� ��� cateCode�� �ص� ����
		  
		 } else {  // lavel 2 = 2�� �з�
		  
		  return dao.list(cateCode);
		  
		 }
	}

	@Override
	public GoodsViewVO goodsView(int gdsNum) throws Exception {
		
		return dao.goodsView(gdsNum);
	}




	

	


}



