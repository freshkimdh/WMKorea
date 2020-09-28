package com.wmk.ex.service;


import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;



import com.wmk.ex.persistence.MemberDAO;
import com.wmk.ex.vo.MemberVO;


import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@AllArgsConstructor
@Log4j 
@Service
public class MemberServiceImpl implements MemberService {
	
	@Inject
	private MemberDAO dao;
	
	@Override
	public void signup(MemberVO vo) throws Exception {
		dao.signup(vo);
		
	}
	
	//로그인
	@Override
	public MemberVO signin(MemberVO vo) throws Exception {
		return dao.signin(vo);
	}

	//로그아웃
	@Override
	public void signout(HttpSession session) throws Exception {
		
		session.invalidate();
	}
	


}



