package com.wmk.ex.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Delete;

import com.wmk.ex.page.Criteria;
import com.wmk.ex.vo.BoardVO;
import com.wmk.ex.vo.MemberVO;
import com.wmk.ex.vo.ReplyVO;


public interface MemberService {
	
	//ȸ������
	public void signup(MemberVO vo) throws Exception;
	
	//�α��� 
	public MemberVO signin(MemberVO vo) throws Exception;
	
	//�α׾ƿ�
	public void signout(HttpSession session) throws Exception;
	
}
