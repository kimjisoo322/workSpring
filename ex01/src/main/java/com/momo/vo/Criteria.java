package com.momo.vo;

import lombok.Data;

// 검색 조건  + 한페이지에 몇건 보여주고, 요청 페이지가 몇건 보여주는지 
@Data
public class Criteria {

	private String searchWorld;
	private String searchField;
	
	// 1 페이지에 10건 보여주기  
	private int pageNo =1;   // 요청 페이지 번호  ( pageNo에 따라 시작번호와 끝번호 생성 ) 
	private int amount =10;  // 한 페이지당 게시물 수 
	
	private int startNo = 1;
	private int endNo = 10;
	
	// private 은 직접 접근을 막아둠
	
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
		if(pageNo > 0) {
			//   ( pageNo에 따라 시작번호와 끝번호 생성 ) 
			endNo = pageNo * amount;
			startNo =(pageNo * amount) - (amount -1);
		}else {
			this.pageNo = 1;
		}
	}

}
