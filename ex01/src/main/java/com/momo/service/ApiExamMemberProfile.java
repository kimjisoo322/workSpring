package com.momo.service;

// 네이버 API 예제 - 회원프로필 조회
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ApiExamMemberProfile {


    public static Map<String, Object> getMemberProfile (String access_token) {
    	
    	// 로그인 후 받아온 토근 정보 
        String token =  access_token;  
        
        // 네이버 로그인 접근 토큰;
        //"AAAAN7QKXf3JlIbdVOKXR8icHQBVzBUUVu8b5V0AE_elmQF8OYYjB2s5H_3Q0rkRdjqtBARvWxT_JByTi_fhOYkE46Q"; 
        String header = "Bearer " + token; // Bearer 다음에 공백 추가


        String apiURL = "https://openapi.naver.com/v1/nid/me";


        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", header);
        String responseBody = get(apiURL,requestHeaders);


        System.out.println(responseBody);
        
        // JSON 문자열을 MAP으로 반환 
        Map<String, Object> map = new HashMap<String, Object>();
        //  -> response 는 object 타입으로 갖고 있기 때문에  String Object로 변환
        // Jackson 라이브러리 사용 
        ObjectMapper objectMapper = new ObjectMapper();
        try {
			map = objectMapper.readValue(responseBody, Map.class);
				// map으로 읽어서 map으로 반환
			System.out.println("responseBody : " + responseBody );
			System.out.println(map.get("resultcode") + " resultcode !");
			System.out.println(map.get("message") + " message !");
			System.out.println(map.get("response") + " response !");
			
			System.out.println( " response (사용자 정보)"  + map.get("response") );
			Map<String, String> response =  (Map<String, String>) map.get("response");
			System.out.println(response.get("id"));
			System.out.println(response.get("profile_image"));
			System.out.println(response.get("mobile"));
			System.out.println(response.get("mobile_e164"));
			System.out.println(response.get("name"));
		
			
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        // 토근을 받아온 결과 map을 반환! 
        return map;
    }


    private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
    public static void main(String[] args) {
        String token =  "AAAAN7QKXf3JlIbdVOKXR8icHQBVzBUUVu8b5V0AE_elmQF8OYYjB2s5H_3Q0rkRdjqtBARvWxT_JByTi_fhOYkE46Q";
		getMemberProfile(token);
	}
}
