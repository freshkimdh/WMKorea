package com.wmk.ex.vo;

import java.util.Date;

public class CommentVO {
	
	private int gdsNum;		//    gdsNum      number          not null,
	private String userId;		//    userId      varchar2(50)    not null,
	private int repNum;		//    repNum      number          not null,
	private String repCon;		//    repCon      varchar2(2000)  not null,
	private Date repDate;		//    repDate     date            default sysdate,
	
	
	public CommentVO() {
		
	}

	
	public int getGdsNum() {
		return gdsNum;
	}
	public void setGdsNum(int gdsNum) {
		this.gdsNum = gdsNum;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getRepNum() {
		return repNum;
	}
	public void setRepNum(int repNum) {
		this.repNum = repNum;
	}
	public String getRepCon() {
		return repCon;
	}
	public void setRepCon(String repCon) {
		this.repCon = repCon;
	}
	public Date getRepDate() {
		return repDate;
	}
	public void setRepDate(Date repDate) {
		this.repDate = repDate;
	}
	
	
	
	
}