package com.momo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.momo.vo.FileVO;

@Service
public interface FileService {
	
	// 등록
	public int fileInsert(FileVO filevo);
	
	// 조회
	public List<FileVO> fileSelect(int bno);
	
	// 파일 삭제 
	public int fileDelete(@Param("bno")int bno, @Param("uuid")String uuid);
}
