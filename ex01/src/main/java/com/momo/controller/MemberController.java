package com.momo.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.momo.service.MemberService;
import com.momo.vo.Member;

import jdk.internal.org.jline.utils.Log;

@Controller
public class MemberController extends CommonRestController {

	@Autowired
	MemberService memberService;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		// 세션 무효화 처리
		session.invalidate();
		return "login";
	}
			  
	@PostMapping("/loginAction") 
	public @ResponseBody Map<String, Object> loginAction(@RequestBody Member member, Model model, HttpSession session) { 
		System.out.println("id : " + member.getId());
		System.out.println("pw: " + member.getPw());
		
		member = memberService.memLogin(member);
		
		if(member != null) {
			// member 객체를 session에 저장 => ${member}로 부르기 
			session.setAttribute("member", member);
			session.setAttribute("userId", member.getId());

			Map<String, Object> map = responseMap(REST_SUCCESS, "로그인 되었습니다.");

			if(member.getRole() != null && member.getRole().contains("ADMIN_ROLE")) {
				// 관리자가 로그인하면 -> 관리자 페이지로 이동/ 사용자가 로그인하면 -> 사용자 페이지로 이동 
				map.put("url", "/admin/main");
			}else {
				map.put("url", "/board/list_boot");
			}
			return map;
			//return responseMap(REST_SUCCESS, "로그인 성공🎉");
		}else {
			return responseMap(REST_FAIL, "아이디와 비밀번호가 일치하지 않습니다");
		}
	
	}
	
	// idCheck url이 호출되면 아래의 메서드가 실행됨!! 
	// 아이디의 count(*) = 0 이면 아이디 중복체크 성공 (why? 현재 그 아이디의 개수가 없다는 건 사용할 수 있다는 뜻이니까!
	@PostMapping("/idCheck")
	public @ResponseBody Map<String, Object> idCheck(@RequestBody Member member){
		int res = memberService.idCheck(member);
		
		// select count(*) = 0이면 
		if(res == 0) {
			return responseMap(REST_SUCCESS, "사용 가능한 아이디입니다.");
		}else {
			return responseMap(REST_FAIL, "이미 사용중인 아이디입니다.");
		}
		
	}
	
	// 회원가입
	@PostMapping("/signUp")
	public @ResponseBody Map<String, Object> memSignUp(@RequestBody Member member){
		try {
			
			int res = memberService.memSignUp(member);
			return responseWriteMap(res);
		} catch (Exception e) {
			e.printStackTrace();
			return responseMap(REST_FAIL, "등록 중 예외사항이 발생하였습니다.");
		}
		
	
	}

}
