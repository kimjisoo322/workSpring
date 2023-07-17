package com.momo.interceptor;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.momo.vo.Member;
/**
 * 권한 체크 
 *  ( 로그인한 사용자의 권한을 체크해서 ADMIN_ROLE 권한이 포함되어 있으면 관리자 ) 
 * */
// servlet-context에서 bean을 생성했기 때문에 interceptor가 실행될 수 있는 것 

@Component
public class AdminInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		if(session.getAttribute("member") != null && session.getAttribute("member").equals("")) {
			Member member = (Member) session.getAttribute("member");
			
			// role 권한 가져오기 
			List<String> role =  member.getRole();
				// roel 에 ADMIN_ROLE이 포함되어있다면
			if(role.contains("ADMIN_ROLE")) {
				return true;
			}
		}
		// 로그인 페이지로 이동
		String message = URLEncoder.encode("로그인 후 사용가능한 메뉴입니다", "utf-8");
		response.sendRedirect("/login?message="+message);
		return false;
	}
}
