package com.wmk.ex.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


public class CartVO {

//	create table tbl_cart (
//		    cartNum number not null,
//		    userId varchar2(50) null,
//		    gdsNum number not null,
//		    cartStock number not null,
//		    addDate date default sysdate,
//		    primary key(cartNum, userId)
//		);
//	
	
	private String userId;
	private int gdsNum;
	private int cartSrock;
	private Date addDate;
	
	public CartVO() {
		
	}
	
	public CartVO(String userId, int gdsNum, int cartSrock, Date addDate, int cartNum) {
		super();
		this.userId = userId;
		this.gdsNum = gdsNum;
		this.cartSrock = cartSrock;
		this.addDate = addDate;
		this.cartNum = cartNum;
	}
	

	private int cartNum;
	public int getCartNum() {
		return cartNum;
	}
	public void setCartNum(int cartNum) {
		this.cartNum = cartNum;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getGdsNum() {
		return gdsNum;
	}
	public void setGdsNum(int gdsNum) {
		this.gdsNum = gdsNum;
	}
	public int getCartSrock() {
		return cartSrock;
	}
	public void setCartSrock(int cartSrock) {
		this.cartSrock = cartSrock;
	}
	public Date getAddDate() {
		return addDate;
	}
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	
	

	
	
	
	
}
