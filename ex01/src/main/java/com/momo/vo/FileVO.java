package com.momo.vo;

import lombok.Data;
// 파일 업로드 객체(vo)
@Data
public class FileVO {
	
	private String uuid;
	private String uploadpath;
	private String filename;
	private String filetype;  
	private int bno;
	
	// 저장된 파일 경로 (uploadpath + uuid + "_" + filename;)
	private String savePath; 	// 저장 경로
	private String s_savePath; // 썸네일경로
	
}
