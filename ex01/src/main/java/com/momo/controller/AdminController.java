package com.momo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
	
	// admin 폴더 안에 main.jsp 파일로 이동
	@GetMapping("/admin/main")
	public String admin() {
		return "/admin/main";
		
	}
	
}
