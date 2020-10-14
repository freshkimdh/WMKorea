package com.wmk.ex.service;


import java.util.List;
import org.springframework.stereotype.Service;

import com.wmk.ex.mapper.AdminMapper;
import com.wmk.ex.vo.CartVO;
import com.wmk.ex.vo.CategoryVO;
import com.wmk.ex.vo.GoodsVO;
import com.wmk.ex.vo.GoodsViewVO;


import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@AllArgsConstructor
@Log4j 
@Service
public class AdminServiceImpl implements AdminService {
	
	
	private AdminMapper adminMapper;
	
	//카테고리
	@Override
	public List<CategoryVO> category() throws Exception {
		
		return adminMapper.category();
	}

	@Override
	public void register(GoodsVO vo) throws Exception {
		adminMapper.register(vo);
		
	}
	
	//상품 목록
	@Override
	public List<GoodsViewVO> goodslist() throws Exception {
		log.info("서비스");
		return adminMapper.goodslist();
		
	}

	@Override
	public GoodsViewVO goodsView(int gdsNum) throws Exception {
		return adminMapper.goodsView(gdsNum);
	}

	@Override
	public void goodsModify(GoodsVO vo) throws Exception {
		adminMapper.goodsModify(vo);
		
	}

	@Override
	public void goodsDelete(int gdsNum) throws Exception {
		adminMapper.goodsDelete(gdsNum);
		
	}

	@Override
	public void addCart(CartVO cart) throws Exception {
		adminMapper.addCart(cart);
	}
	


}



