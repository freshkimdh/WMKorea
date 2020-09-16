package com.wmk.ex.page;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class Criteria { //표준, 기준
	//페이징처리를 위해서 페이지 번호와 한페이지당 몇개의 데이터를 보여줄 것인가를 결정해야한다.
	private int pageNum; //페이지 번호
	private int amount; //한페이지당 몇개의 데이터를 보여줄 것인가?
	
	
	public Criteria() {
		this(1, 10); //기본값 1페이지 10개로 지정
		
	}
	
	public Criteria(int pageNum, int amount) {
		
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	
	
}
