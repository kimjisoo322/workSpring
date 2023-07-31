package com.momo.service;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.fasterxml.jackson.databind.ObjectMapper;
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
			
			// login에 성공해서 멤버 객체가 생성되었을 때, 사용자 권한을 조회하여 member객체가 가진 role에 넣어준다 (07/17)
		 List<String> role = memberMapper.getMemberRole(member.getId());
		 
		 // 비밀번호 인증이 성공하면 member 객체를 반환 
			if(res) {
				
				// 사용자 권한을 조회 
				member.setRole(role);
				//member.setRole(memberMapper.getMemberRole(member.getId()));
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
	
	// 네이버 로그인
	@Autowired
	ApiExamMemberProfile apiexamMemberProfile;
	@Override
	public void naverLogin(HttpServletRequest request, Model model) {
		// callback 처리 (-> access_token 얻어오기)
		
		try {
			Map<String, String> callbackRes = callback(request);
			// 콜백 결과를 통해 토근 얻어오기 ( 사용자 정보 얻어올 수 있음 ) 
			String access_token = 
					callbackRes.get("access_token");
			Map<String, Object> responseBody = apiexamMemberProfile.getMemberProfile(access_token);
			
			Map<String, String> response =  (Map<String, String>) responseBody.get("response");
			
			System.out.println(response.get("id"));
			System.out.println(response.get("name"));
			System.out.println(response.get("gender"));
			System.out.println("=====================================");
			
			// member 세션에 저장
			model.addAttribute("id", response.get("id"));
			model.addAttribute("name", response.get("name"));
			model.addAttribute("gender", response.get("gender"));
			model.addAttribute("mobile", response.get("mobile"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		// access_token -> 사용자 정보 조회 
		
	}
	public Map<String, String> callback(HttpServletRequest request) throws Exception{
		  String clientId = "IjasFTsL5ZtTTWvs1Sce";//애플리케이션 클라이언트 아이디값";
		    String clientSecret = "4XrYJiNdfd";//애플리케이션 클라이언트 시크릿값";
		    String code = request.getParameter("code");
		    String state = request.getParameter("state");
		    try {
		    String redirectURI = URLEncoder.encode("http://localhost:8080/login/naver_callback", "UTF-8");
		    String apiURL;
		    apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
		    apiURL += "client_id=" + clientId;
		    apiURL += "&client_secret=" + clientSecret;
		    apiURL += "&redirect_uri=" + redirectURI;
		    apiURL += "&code=" + code;
		    apiURL += "&state=" + state;
		    String access_token = "";
		    String refresh_token = "";
		    System.out.println("apiURL="+apiURL);
		      URL url = new URL(apiURL);
		      HttpURLConnection con = (HttpURLConnection)url.openConnection();
		      con.setRequestMethod("GET");
		      int responseCode = con.getResponseCode();
		      BufferedReader br;
		      System.out.print("responseCode="+responseCode);
		      if(responseCode==200) { // 정상 호출
		        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		      } else {  // 에러 발생
		        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		      }
		      String inputLine;
		      StringBuffer res = new StringBuffer();
		      while ((inputLine = br.readLine()) != null) {
		        res.append(inputLine);
		      }
		      br.close();
		      if(responseCode==200) {
		    	  // 결과값 
		       System.out.println(" token 요청  :"+res.toString());
		       Map<String, String> map = new HashMap<String, String>();
		       ObjectMapper objmapper = new ObjectMapper();
		       map = objmapper.readValue(res.toString(), Map.class);
		       return map;
		      }else {
		    	  System.out.println("오 류 발 생 ");
		    	 throw new Exception("callback 반환코드 : " +  responseCode);
		      }
		    } catch (Exception e) {
		      System.out.println(e);
		      throw new Exception("callback 처리중 예외 사항이 발생!!");
		    }
		    
	}
	
}
