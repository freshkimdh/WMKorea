package com.wmk.ex.mapper;

import javax.servlet.http.HttpSession;

import com.wmk.ex.vo.MemberVO;

public interface MemberMapper {
	
	//ȸ������
	public void signup(MemberVO vo) throws Exception;
	
	//�α��� 
	public MemberVO signin(MemberVO vo) throws Exception;
	
	//�α׾ƿ�
	public void signout(HttpSession session) throws Exception;
	
	
}
