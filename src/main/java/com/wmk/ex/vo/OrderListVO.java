package com.wmk.ex.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderListVO {
	
	//OrderVO의 모든값
	private String orderId;				//    orderId     varchar2(50) not null,
	private String userId;				//    userId      varchar2(50) not null,
	private String OrderRec;			//    orderRec    varchar2(50) not null,
	private String userAddr1;			//    userAddr1   varchar2(20) not null,
	private String userAddr2;			//    userAddr2   varchar2(50) not null,
	private String userAddr3;			//    userAddr3   varchar2(50) not null,
	private String orderPhon;			//    orderPhon   varchar2(30) not null,
	private int amount;					//    amount      number       not null,
	private Date oderDate;				//    orderDate   Date         default sysdate,   
										//    primary key(orderId)	
	
	//OrderDetilsVO의 모든값
	private int orderDetailsNum;
	private int gdsNum;
	private int cartStock;
	
	//GoodsVO의 정보
	private String gdsName;
	private String gdsImg;
	private int gdsPrice;
	

}
