package com.momo.vo;

import java.util.List;

import lombok.Data;

@Data
public class BoardVO {
	private int bno;
	private String title;
	private String content;
	private String writer;
	private String regdate;
	private String updatedate;
	
	private String replycnt; // 댓글 수 증가

	private List<FileVO> attachFile; // 여러개의 첨부파일을 가지도록 
}
