package com.momo.vo;

import lombok.Data;

@Data
public class Book {
	
	private int no;  		// 도서번호 ( 공백 시 오류 )
	private String title;	// 제목
	private String author;  // 저자명
	private String publisher; // 출판사
	
	private String rentyn;  
	private String rentStr;  // y면 대여중 이라고 문구주기  
	private String visitcount; // 조회수 
	
	private String ofile;  //원본파일명
	private String sfile;  //저장된파일명
	
	private String rentno; // 대여번호
	private String rentid; // 대여자 아이디
	private String startdate; // 대여일
	private String returndate; //반납일
	private String enddate; // 반납가능일
}
