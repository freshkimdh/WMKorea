package com.wmk.ex.vo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor 
@AllArgsConstructor
public class LikeVO {

	private int bLikeNumber;
	private int bId;
	private String id;
	
	public int getbLikeNumber() {
		return bLikeNumber;
	}
	public void setbLikeNumber(int bLikeNumber) {
		this.bLikeNumber = bLikeNumber;
	}
	public int getbId() {
		return bId;
	}
	public void setbId(int bId) {
		this.bId = bId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	@Override
	public String toString() {
		return "LikeVO [bLikeNumber=" + bLikeNumber + ", bId=" + bId + ", id=" + id + "]";
	}
	
	
	
	
}
