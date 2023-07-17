package com.momo.interceptor;

import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 	Spring Interceptor 
 *   : HTTP 요청 처리 과정에서 요청을 가로채고 처리 전후에 추가 작업을 수행 
 *   
 *   인터셉터는 컨트롤러(controller)에 진입하기 전, 
 *     컨트롤러 실행 후 뷰(view) 렌더링 전 등 다양한시점에서 동작 , 사용하여 요청의 처리 흐름을 제어하거나 조작 
 *   
 *   [♣ 인증 및 권한 체크로직 작성해보자 ♣]
 * 
 * */
@Component
public class LoginInterceptor implements HandlerInterceptor{
	
	/**
	 * 	preHandle : 컨트롤러 실행 전에 실행 
	 *  return : true : 요청 컨트롤러 실행 
	 *  		 false: 요청 컨트롤러 실행하지 않음 
	 * */
	@Override
									// session에 ID가 있는지 판단 
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		if(session.getAttribute("userId") != null && !session.getAttribute("userId").equals("")){
			return true;
		}else{
			String message = URLEncoder.encode("로그인 후 사용가능한 메뉴입니다", "utf-8");
						  // 로그인이 되어있지 않은 사용자는 로그인 페이지로 이동 
			response.sendRedirect("/login?message="+message);
			return false;
		}
		//return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}
