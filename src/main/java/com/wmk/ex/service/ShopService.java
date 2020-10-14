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
	
	
	// �ֹ�����
	public void orderInfo(OrderVO order) throws Exception;

	//�ֹ� �� ����
	public void orderInfo_Details(OrderDetailVO orderDetail) throws Exception;
	
	//īƮ���� 
	public void cartAllDelete(String userId) throws Exception;
	
	//Ư�� ������ �ֹ� ��Ϻ���
	public List<OrderVO> orderList(OrderVO order) throws Exception;
	
	//Ư�� ū �ֹ��� �󼼳��� ����
	public List<OrderListVO> orderView(OrderVO order) throws Exception;
	

}
