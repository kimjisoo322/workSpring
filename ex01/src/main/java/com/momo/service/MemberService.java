package com.momo.service;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.momo.vo.Member;

@Service
public interface MemberService {
		
	// 로그인 
	public Member memLogin(Member member);
	
	// 회원가입
	public int memSignUp(Member member);
	
	// 아이디 중복체크 
	public int idCheck(Member member);

	// 네이버 로그인 
	public void naverLogin(HttpServletRequest request, Model model);
}
