package com.momo.service;


import org.springframework.stereotype.Service;

import com.momo.vo.Member;

@Service
public interface MemberService {
		
	// 로그인 
	public Member memLogin(Member member);
	
	// 회원가입
	public int memSignUp(Member member);
	
	// 아이디 중복체크 
	public int idCheck(Member member);
}
