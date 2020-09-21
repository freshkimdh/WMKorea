package com.wmk.ex.vo;

import java.sql.Timestamp;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor 
@AllArgsConstructor
public class ReplyVO {

//    bId number not null,
//    rId number not null,
//    content varchar2(1000) not null,
//    writer varchar2(50) not null,
//    regdate date default sysdate,
//    primary key(bId, rId)
	
	
	
	int bId;
	int rId;
	String Content;
	String Writer;
	Timestamp regDate;
	
	public int getbId() {
		return bId;
	}
	public void setbId(int bId) {
		this.bId = bId;
	}
	public int getrId() {
		return rId;
	}
	public void setrId(int rId) {
		this.rId = rId;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getWriter() {
		return Writer;
	}
	public void setWriter(String writer) {
		Writer = writer;
	}
	public Timestamp getRegDate() {
		return regDate;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	
	@Override
	public String toString() { // ´ñ±Û Ã³¸®
		return "ReplyVO [bid=" + bId + ", rid=" + rId + ", content=" + Content + ", writer=" + Writer + ", regdate="
				+ regDate + "]";
	}
	
}
