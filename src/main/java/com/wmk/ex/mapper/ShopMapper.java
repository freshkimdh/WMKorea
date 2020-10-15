package com.wmk.ex.mapper;

import java.util.List;

import com.wmk.ex.vo.CartListVO;
import com.wmk.ex.vo.CartVO;
import com.wmk.ex.vo.CategoryVO;
import com.wmk.ex.vo.GoodsVO;
import com.wmk.ex.vo.GoodsViewVO;
import com.wmk.ex.vo.OrderDetailVO;
import com.wmk.ex.vo.OrderListVO;
import com.wmk.ex.vo.OrderVO;
import com.wmk.ex.vo.UserVO;

public interface ShopMapper {
	
//	//ī�װ��� ��ǰ ����Ʈ
//	public List<GoodsViewVO> list(int cateCode) throws Exception;
//	
//	//��ǰ��ȸ
//	public GoodsViewVO goodslist(int gdsNum) throws Exception;
	

	public UserVO userIdread(String id) throws Exception;
	
	//��ٱ��� ����
	public List<CartListVO> cartList(String username);
	
	//��ٱ��� ����
	public void deleteCart(CartVO cart) throws Exception;
	
	//�ֹ�����
	public void orderInfo(OrderVO order) throws Exception;
		
	//�ֹ� ������
	public void orderInfo_Details(OrderDetailVO orderDetail) throws Exception;
	
	//�ֹ��� īƮ ����
	public void cartAllDelete(String userId) throws Exception;
	
	//Ư�� ������ �ֹ���� ����
	public List<OrderVO> orderList(OrderVO order) throws Exception;
	
	//Ư�� ū �ֹ��� �󼼳��� ����
	public List<OrderListVO> orderView(OrderVO order) throws Exception;
	

}
