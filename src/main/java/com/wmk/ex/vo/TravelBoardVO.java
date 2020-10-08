package com.wmk.ex.vo;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor 
@AllArgsConstructor
public class TravelBoardVO {

	int hId;
	String hNAME;
	String hTitle;
	String hContent;
	Timestamp hDate;
	String hOpenTime;
	String hAdress;
	String hCategorie;
	int hHit;
	int hGroup;
	int hStep;
	int hIndent;
	
	
	
	public int gethId() {
		return hId;
	}
	public void sethId(int hId) {
		this.hId = hId;
	}
	public String gethNAME() {
		return hNAME;
	}
	public void sethNAME(String hNAME) {
		this.hNAME = hNAME;
	}
	public String gethTitle() {
		return hTitle;
	}
	public void sethTitle(String hTitle) {
		this.hTitle = hTitle;
	}
	public String gethContent() {
		return hContent;
	}
	public void sethContent(String hContent) {
		this.hContent = hContent;
	}
	public Timestamp gethDate() {
		return hDate;
	}
	public void sethDate(Timestamp hDate) {
		this.hDate = hDate;
	}
	public String gethOpenTime() {
		return hOpenTime;
	}
	public void sethOpenTime(String hOpenTime) {
		this.hOpenTime = hOpenTime;
	}
	public String gethAdress() {
		return hAdress;
	}
	public void sethAdress(String hAdress) {
		this.hAdress = hAdress;
	}
	public String gethCategorie() {
		return hCategorie;
	}
	public void sethCategorie(String hCategorie) {
		this.hCategorie = hCategorie;
	}
	public int gethHit() {
		return hHit;
	}
	public void sethHit(int hHit) {
		this.hHit = hHit;
	}
	public int gethStep() {
		return hStep;
	}
	public void sethStep(int hStep) {
		this.hStep = hStep;
	}
	public int gethIndent() {
		return hIndent;
	}
	public void sethIndent(int hIndent) {
		this.hIndent = hIndent;
	}
	public int gethGroup() {
		return hGroup;
	}
	public void sethGroup(int hGroup) {
		this.hGroup = hGroup;
	}
	@Override
	public String toString() {
		return "TravelBoardVO [hId=" + hId + ", hNAME=" + hNAME + ", hTitle=" + hTitle + ", hContent=" + hContent
				+ ", hDate=" + hDate + ", hOpenTime=" + hOpenTime + ", hAdress=" + hAdress + ", hCategorie="
				+ hCategorie + ", hHit=" + hHit + ", hGroup=" + hGroup + ", hStep=" + hStep + ", hIndent=" + hIndent
				+ "]";
	}
	
	
	
	
	
	
	
}
