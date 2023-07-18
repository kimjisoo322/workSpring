package com.momo.mapper;

import java.util.List;

import com.momo.vo.FileVO;

public interface FileMapper {
	
	// 등록
	public int fileInsert(FileVO filevo);
	
	// 조회 (게시물 당 첨부파일의 개수)
	public List<FileVO> fileSelect(int bno);
}
