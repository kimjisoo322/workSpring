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

@Controller
public class MemberController extends CommonRestController {

	@Autowired
	MemberService memberService;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}

			  
	@PostMapping("/loginAction") 
	public @ResponseBody Map<String, Object> loginAction(@RequestBody Member member, Model model, HttpSession session) { 
		System.out.println("id : " + member.getId());
		System.out.println("pw: " + member.getPw());
		
		member = memberService.memLogin(member);
		
		if(member != null) {
			session.setAttribute("member", member);
			session.setAttribute("userId", member.getId());
			return responseMap(1, "로그인");
		}else {
			return responseMap(0, "로그인");
		}
	
	}
			

}
