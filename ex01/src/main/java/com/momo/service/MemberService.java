package com.momo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.momo.vo.Member;

@Service
public interface MemberService {
		
	// 로그인 
	public Member memLogin(Member member);

}
