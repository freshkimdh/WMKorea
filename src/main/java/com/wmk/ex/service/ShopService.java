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
	
	//ī�װ��� ��ǰ ����Ʈ
	public List<GoodsViewVO> list(int cateCode, int level) throws Exception;
	
	//��ǰ ��ȸ
	public GoodsViewVO goodsView(int gdsNum) throws Exception;
	
	//��ǰ ��� �ۼ�
	public void registReply(CommentVO comment) throws Exception;
	
	//��ǰ �Ұ�(���) ����Ʈ
	public List<CommentListVO> commentList(int gdsNum) throws Exception;
	
	//��ǰ �Ұ�(���) ����
	public void deleteReply(CommentVO comment) throws Exception;
	
	//���̵� üũ
	public String idCheck(int repNum) throws Exception;
	
	//��ٱ��� ���
	public void addCart(CartVO cart) throws Exception;
	
	//��ٱ��� ����
	public List<CartListVO> cartList(String username) throws Exception;
	
	//��ٱ��� ����
	public void deleteCart (CartVO cart) throws Exception;

}
