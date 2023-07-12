package com.momo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.momo.service.ReplyService;
import com.momo.vo.Criteria;
import com.momo.vo.PageDto;
import com.momo.vo.ReplyVO;

import lombok.extern.log4j.Log4j;


 // 댓글 달기 
// 컨트롤러는 기본적으로 jsp를 반환/ 하지만 json을 반환하기 위해 RestController 사용
/**
 * 	RestController : controller가 가진 rest 방식을 처리하기 위함을 명시
 * */
@Log4j
@RestController
public class ReplyController {
	
	@Autowired
	ReplyService replyService;
	
	//  -  /test 경로 요청 받았을 때, "test" 문자열 출력
	@GetMapping("/test")
	public String test() {
		return "test";
	}
	
	// 댓글 조회  +  페이징 처리 (23/07/11)
	/** 
	 * 	PathVariable : ( 여러개 변수로 사용 가능 ) 
	 *                -> URL 경로의 일부분을 파라메터로 사용 
	 *       /reply/list/83
	 *    URL 경로에 있는 값을 파라메터로 추출하려고 할 때 사용   => 요청할 때 url경로에 파라메터 값을 전달 ! 
	 * */
	@GetMapping("/reply/list/{bno}/{page}")
	public Map<String, Object> getList(@PathVariable("bno") int bno, @PathVariable("page") int page){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 페이징 처리 
		Criteria criteria = new Criteria();
		criteria.setPageNo(page);
		
		// tbl_reply 테이블의 목록 
		List<ReplyVO> list = replyService.getList(bno, criteria);
		int totalCnt = replyService.totalCnt(bno);
		
		// 페이지 블럭 생성
		PageDto pageDto = new PageDto(criteria, totalCnt);
		
		map.put("list", list);
		map.put("pageDto", pageDto);
		map.put("criteria", criteria);
		
		return map;
	}
	
	// 댓글 등록
	/**
	 * 	RequestBody : 
	 *      (하나의 변수, 파라메터만 받을 수 있음 ) 
	 *    JSON 데이터를 원하는 타입으로 바인딩 처리  
	 *   
	 * */
	@PostMapping("/reply/insert")
	public Map<String, Object> insertReply(@RequestBody ReplyVO reply){
		log.info(" ============= 댓글 ============= ");
		log.info("replyVo" + reply);
	
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			int res = replyService.insertReply(reply);
			if(res>0) {
				map.put("result", "success");
			}else {
				map.put("result", "fail");
				map.put("message", "등록 중 오류 발생!");
			}
			
		}catch(Exception e) {
			map.put("result", "fail");
			map.put("message", "댓글 등록 중 오류 발생");
		}
		return map;
	}
	
	// 댓글 삭제
	@GetMapping("/reply/delete/{rno}")
	public Map<String, Object> deleteReply(@PathVariable("rno") int rno) {
		Map<String, Object> map = new HashMap<String, Object>();

		
		int res = replyService.deleteReply(rno);
		
		if(res > 0) {
			map.put("result", "success");
		}else {
			map.put("result", "fail");
			map.put("message", "삭제 중 오류 발생!");
		}
		
		return map;
	}
	
	// 댓글 수정 
	@PostMapping("/reply/update") 
	 public Map<String, Object> updateReply(@RequestBody ReplyVO reply){
		 Map<String, Object> map = new HashMap<String, Object>();
		 
		 int res = replyService.updateReply(reply);
		 
		 log.info("================댓글 수정=========" + res);
		 if(res>0) {
			 map.put("result", "success");
		 }else {
			 map.put("result", "fail");
			 map.put("message", "수정 중 오류 발생!");
		 }
		 return map;
	 }
	
}
