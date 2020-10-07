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
	
	//회원가입
	public void signup(MemberVO vo) throws Exception;
	
	//로그인 
	public MemberVO signin(MemberVO vo) throws Exception;
	
	//로그아웃
	public void signout(HttpSession session) throws Exception;
	
}
