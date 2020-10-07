package com.wmk.ex.dao;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.wmk.ex.vo.CommentVO;
import com.wmk.ex.vo.GoodsViewVO;

@Repository
public class ShopDAOImpl implements ShopDAO {

	@Inject
	private SqlSession sql;
	
	private static String namespace = "com.wmk.ex.mapper.ShopMapper";

	@Override
	public List<GoodsViewVO> list(int cateCode, int cateCodeRef) throws Exception {
	 
	 HashMap<String, Object> map = new HashMap<String, Object>();
	 
	 map.put("cateCode", cateCode);
	 map.put("cateCodeRef", cateCodeRef);
	 
	 return sql.selectList(namespace + ".list_1", map);
	}

	// 카테고리별 상품 리스트 : 2차 분류
	@Override
	public List<GoodsViewVO> list(int cateCode) throws Exception {
	 
	 return sql.selectList(namespace + ".list_2", cateCode);
	}

	@Override
	public GoodsViewVO goodsView(int gdsNum) throws Exception {
		
		return sql.selectOne("com.wmk.ex.mapper.AdminMapper"
				+ ".goodsView", gdsNum);
	}
	
	
	//상품 댓글 작성
	@Override
	public void registReply(CommentVO comment) throws Exception {
		
		sql.insert(namespace + ".registReply", comment);
		
	}


	
	




 
}  