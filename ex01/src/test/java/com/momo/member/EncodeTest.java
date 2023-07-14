package com.momo.member;

import java.beans.Encoder;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// 암호화
public class EncodeTest {
	 
	/**
	 * 	스프링 시큐리티모듈에서 제공하는 비밀번호 암호화 및 인증 기능 
	 *  (Spring Security) 에서 제공하는 비밀번호를 암호화하는데 사용할 수 있는 메서드를 가진 클래스 
	 * 	
	 *  pom.xml에 spring security 라이브러리 추가
	 * 
	 * */
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	@Test
	public void test() {
		String pw = "안녕하세요";

		for(int i =0; i<10; i++) {
			// 암호화할때마다 새로운 문자열을 반환하며, 복호화가 불가능! 
			String encodePw = encoder.encode(pw);
			System.out.println(encodePw);
			
			// 첫번째 매개변수 : 일치 여부를 확인하고자 하는 인코딩 되지 않는 패스워드 (pw = 평문자열)
			// 두번째 매개변수 : 인코딩 된 패스워드를 입력 (encodePw = 인코딩된)
			
			boolean matches = encoder.matches(pw, encodePw);
			System.out.println("인증결과 : " + matches);
			
			/* 결과) -> 제출된 인코딩 되지 않는 패스워드(일치 여부를 확인하고자 하는 패스워드)와 인코딩된 패스워드의 일치여부 확인
			 * */
		}
		
	}
	
}
