package com.wmk.ex.persistence;

import com.wmk.ex.vo.MemberVO;

public interface MemberDAO {
	
	//ȸ������
	public void signup(MemberVO vo) throws Exception; 
	
	//�α���
	public MemberVO signin(MemberVO vo) throws Exception; 

}
