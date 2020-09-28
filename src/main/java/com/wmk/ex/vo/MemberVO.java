package com.wmk.ex.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class MemberVO {

	
	private String userId;      //varchar2(50) not null,
	private String userPass;    //varchar2(100)   not null,
	private String userName;    //varchar2(30)    not null,
	private String userPhon;    //varchar2(20)    not null,
	private String userAddr1;   //varchar2(20)    null,
	private String userAddr2;   //varchar2(50)    null,
	private String userAddr3;   //varchar2(50)    null,
	private Date regiDate;    //date            default sysdate,
	private int verify;      //number          default 0,
		   
	
	public MemberVO() {
		
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getUserPass() {
		return userPass;
	}


	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserPhon() {
		return userPhon;
	}


	public void setUserPhon(String userPhon) {
		this.userPhon = userPhon;
	}


	public String getUserAddr1() {
		return userAddr1;
	}


	public void setUserAddr1(String userAddr1) {
		this.userAddr1 = userAddr1;
	}


	public String getUserAddr2() {
		return userAddr2;
	}


	public void setUserAddr2(String userAddr2) {
		this.userAddr2 = userAddr2;
	}


	public String getUserAddr3() {
		return userAddr3;
	}


	public void setUserAddr3(String userAddr3) {
		this.userAddr3 = userAddr3;
	}


	public Date getRegiDate() {
		return regiDate;
	}


	public void setRegiDate(Date regiDate) {
		this.regiDate = regiDate;
	}


	public int getVerify() {
		return verify;
	}


	public void setVerify(int verify) {
		this.verify = verify;
	}
	
	
	
}
