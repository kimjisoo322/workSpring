package com.momo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.momo.mapper.MemberMapper;
import com.momo.vo.Member;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberMapper memberMapper;
	
	@Autowired
	BCryptPasswordEncoder encoder;

	@Override
	public Member memLogin(Member parammember) {
		// 사용자 정보 조회 
		Member member =  memberMapper.memLogin(parammember);
		
		// 사용자 정보가 있다면! 
		if(member != null) {
			
			// 사용자가 입력한 비밀번호, 데이터베이스에 암호화 되어 저장된 비밀번호
			//   => 두 매개변수가 일치하는지 확인하는 작업✨
		boolean res = encoder.matches(parammember.getPw(), member.getPw());
		
		   // 비밀번호 인증이 성공하면 member 객체를 반환 
			if(res) {
				return member;
			}else {
				return null;
			}
		}
		return memberMapper.memLogin(member);
	}

	@Override
	public int memSignUp(Member member) {
		
		// 비밀번호 암호화 
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		// 암호화 된 pw를 member에 다시 넣어주기 위해 set
		member.setPw( encoder.encode(member.getPw()));
		System.out.println("pw : " + member.getPw());
		return memberMapper.memSignUp(member);
	}

	@Override
	public int idCheck(Member member) {
		return memberMapper.idCheck(member);
	}
	
	
}
