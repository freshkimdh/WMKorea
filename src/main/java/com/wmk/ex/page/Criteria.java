package com.wmk.ex.page;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class Criteria { //ǥ��, ����
	//����¡ó���� ���ؼ� ������ ��ȣ�� ���������� ��� �����͸� ������ ���ΰ��� �����ؾ��Ѵ�.
	private int pageNum; //������ ��ȣ
	private int amount; //���������� ��� �����͸� ������ ���ΰ�?
	
	
	public Criteria() {
		this(1, 10); //�⺻�� 1������ 10���� ����
		
	}
	
	public Criteria(int pageNum, int amount) {
		
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	
	
}
