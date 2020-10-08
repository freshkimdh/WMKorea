package com.wmk.ex.vo;

import java.util.Date;

public class FBoardVO {
	

//fBoard_Num NUMBER(20) not null PRIMARY KEY,
//fId varchar2(20) not null,
//fTitle VARCHAR2(100) not null,
//fContent VARCHAR2(255),
//fDate DATE DEFAULT SYSDATE,
//fHit NUMBER(10) DEFAULT 0,
//fGroup NUMBER(4),
//fStep NUMBER(4),
//fIndent NUMBER(4),
//FOREIGN KEY (fId) REFERENCES wmk_users (id) -- 작성자를 id로 나타낸다.
	
	private int fBoard_Num;
	private String fId;
	private String fTitle;
	private String fContent;
	private Date fDate;
	private int fHit;
	private int fGroup;
	private int fStep;
	private int fIndent;
	
	public FBoardVO() {
		
	}

	public int getfBoard_Num() {
		return fBoard_Num;
	}

	public void setfBoard_Num(int fBoard_Num) {
		this.fBoard_Num = fBoard_Num;
	}

	public String getfId() {
		return fId;
	}

	public void setfId(String fId) {
		this.fId = fId;
	}

	public String getfTitle() {
		return fTitle;
	}

	public void setfTitle(String fTitle) {
		this.fTitle = fTitle;
	}

	public String getfContent() {
		return fContent;
	}

	public void setfContent(String fContent) {
		this.fContent = fContent;
	}

	public Date getfDate() {
		return fDate;
	}

	public void setfDate(Date fDate) {
		this.fDate = fDate;
	}

	public int getfHit() {
		return fHit;
	}

	public void setfHit(int fHit) {
		this.fHit = fHit;
	}

	public int getfGroup() {
		return fGroup;
	}

	public void setfGroup(int fGroup) {
		this.fGroup = fGroup;
	}

	public int getfStep() {
		return fStep;
	}

	public void setfStep(int fStep) {
		this.fStep = fStep;
	}

	public int getfIndent() {
		return fIndent;
	}

	public void setfIndent(int fIndent) {
		this.fIndent = fIndent;
	}
	
	
	
}
