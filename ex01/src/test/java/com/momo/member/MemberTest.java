package com.momo.member;

import static org.junit.Assert.assertNotNull;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.momo.mapper.MemberMapper;
import com.momo.vo.Member;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MemberTest {
	
	@Autowired
	MemberMapper memberMapper;
	
	@Test
	public void memLogin() {
		assertNotNull(memberMapper);
		log.info("============멤버 로그인==========");
		Member member = new Member();
		member.setId("JISOO");
		member.setName("김지수");
		member.setPw("1234");
		Member list = memberMapper.memLogin(member);
				
		/*
		 * // 반복문) list로 부터 꺼내온 값을 board에 저장 list.forEach(mem ->{
		 * log.info("memberVo ========"); log.info(mem.getId());
		 * log.info(mem.getName()); log.info(mem.getStatus()); log.info(mem.getGrade());
		 * log.info(mem.getAdminyn()); });
		 */
	}
	
}
