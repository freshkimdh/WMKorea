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
//FOREIGN KEY (fId) REFERENCES wmk_users (id) -- �ۼ��ڸ� id�� ��Ÿ����.
	
	private int fBoard_Num;
	private String fId;
	private String fTitle;
	private String fContent;
	private Date fDate;
	private int fHit;
	private int fGroup;
	private int fStep;
	private int fIndent;
	private int Like_Cnt;
	private int Hate_cnt;
	private int View_cnt;
	
	public FBoardVO() {}
	
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
	public int getLike_Cnt() {
		return Like_Cnt;
	}
	public void setLike_Cnt(int like_Cnt) {
		Like_Cnt = like_Cnt;
	}
	public int getHate_cnt() {
		return Hate_cnt;
	}
	public void setHate_cnt(int hate_cnt) {
		Hate_cnt = hate_cnt;
	}
	public int getView_cnt() {
		return View_cnt;
	}
	public void setView_cnt(int view_cnt) {
		View_cnt = view_cnt;
	}
	
	

	
	
	
}
