package com.momo.vo;

import lombok.Data;

// 페이지 블럭 관련 
@Data
public class PageDto {
	
	private	Criteria criteria;  // 페이지 번호, 페이지당 게시물 수 
	private	int total;  		// 총 게시물의 수 
	private	int startNo;		// 페이지 블럭의 시작 번호
	private	int endNo;			// 페이지 블럭의 끝번호
	
	private	boolean prev, next;	// 이전, 다음 버튼 
	
	private	int realEnd; 		//게시물의 끝 페이지번호_ 진짜 아예 끝번호 ( << 끝페이지로 이동 >> ) 
	
	// 반환타입 x, 클래스명과 같음 = 생성자
	public PageDto(Criteria criteria, int total) {
		this.criteria = criteria;
		this.total = total;
		
		// 끝번호 = ( 요청한 페이지 번호 / 5.0 (5개씩 보여줌) ) * 5 => 페이지 블럭의 끝번호  ==> 25개  
		this.endNo = (int)(Math.ceil(criteria.getPageNo() / 5.0) * 5);
		
		// 시작 번호 = 끝번호 에서 -1 ( 즉 5개중에 1번을 보여주려면 -4개 빼주기)
		this.startNo = this.endNo -4;
	
		// 끝 페이지 블럭 번호 =  총 게시물의 수를 페이지당 게시물의 수로 나눠서 실제 끝 페이지 번호를 구함
		realEnd = (int)(Math.ceil((total*1.0)/criteria.getAmount()));
		endNo = endNo>realEnd? realEnd : endNo;

		// <  앞, 뒤로 이동   >
		prev = startNo > 1 ? true : false;
		next = endNo == realEnd ? false : true;
	
	}
}
