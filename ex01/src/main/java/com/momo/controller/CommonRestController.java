package com.momo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.momo.vo.Criteria;
import com.momo.vo.PageDto;
import com.momo.vo.ReplyVO;

public class CommonRestController {
	
	// 상수 등록 
	private final String REST_WRITE = "등록";
	private final String REST_EDIT = "수정";
	private final String REST_DELETE = "삭제";
	private final String REST_SELECET = "조회";
	
	
	// map을 생성 후 result, msg 세팅 
	/**
	 *	입력, 수정, 삭제의 경우 int 값을 반환 
	 *  결과를 받아서 Map을 생성 후 반환 
	 * */
	public Map<String, Object> responseMap(int res, String message){
		Map<String, Object> map = new HashMap<String, Object>();
	
		if(res > 0) {
			map.put("result", "success");
			map.put("message", message + "되었습니다.");
		}else {
			map.put("result", "fail");
			map.put("message", message + "중 오류 발생!");
		}
		return map;
	}
	
	public Map<String, Object> responseWriteMap(int res){
		return responseMap(res, REST_WRITE);
	}
	public Map<String, Object> responseEditMap(int res){
		return responseMap(res, REST_EDIT);
	}
	public Map<String, Object> responseDeleteMap(int res){
		return responseMap(res, REST_DELETE);
	}
						// <?> 어떤 객체도 상관없이 받을 수 있음
	public Map<String, Object> responseSelectMap(List<?> list, PageDto pageDto, Criteria criteria){
		
		int res = list != null ? 1 : 0;
		Map<String, Object> map = responseMap(res,REST_SELECET);
		map.put("list", list);
		map.put("pageDto", pageDto);
		map.put("criteria", criteria);
		
		return map;
	}
}
