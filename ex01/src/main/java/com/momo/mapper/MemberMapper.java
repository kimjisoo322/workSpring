package com.momo.mapper;


import com.momo.vo.Member;

public interface MemberMapper {

	
	// Member 조회 
	public Member memLogin(Member member);
	
	// Member insert(등록)
	public int memSignUp(Member member);
	
	// id 중복체크 
	public int idCheck(Member member);
}
