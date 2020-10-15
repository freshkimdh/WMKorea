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
public class OrderDetailVO {
	
	
									//	create table wmk_order_details (
	private int	orderDetailsNum;	//	orderDetailsNum number       not null,
	private String orderId;			//	orderId         varchar2(50) not null,
	private int	gdsNum;				//	gdsNum          number          not null,
	private int	cartStock;			//	cartStock       number          not null,
									//	primary key(orderDetailsNum)
									//		);
	
	

}
