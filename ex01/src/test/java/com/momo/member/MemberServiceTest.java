package com.momo.member;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.momo.mapper.MemberMapper;
import com.momo.service.MemberService;
import com.momo.vo.Member;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MemberServiceTest {
	@Autowired
	MemberService memberService;
	
	// 로그인 테스트
	@org.junit.Test
	public void Test() {
		Member member = new Member();
		member.setId("ADMIN");
		member.setPw("1234");
		Member list = memberService.memLogin(member);
		
	}

	/*
	 * @org.junit.Test public void signUp() { System.out.println("=====회원가입=====");
	 * Member member = new Member(); member.setId("JK"); member.setName("전정국");
	 * member.setPw("1234"); int res = memberService.memSignUp(member);
	 * log.info(res); }
	 */
	
	@org.junit.Test
	public void idCheckTest() {
		Member member = new Member();
		member.setId("JIMIN");
		System.out.println("====아이디중복체크");
		
		int res = memberService.idCheck(member);
		log.info(res);
	}
}	
