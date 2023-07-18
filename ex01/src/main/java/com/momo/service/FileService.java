package com.momo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.momo.vo.FileVO;

@Service
public interface FileService {
	
	// 등록
	public int fileInsert(FileVO filevo);
	
	// 조회
	public List<FileVO> fileSelect(int bno);
}
