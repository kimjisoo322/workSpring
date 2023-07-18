package com.momo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.momo.mapper.FileMapper;
import com.momo.vo.FileVO;

@Service
public class FileServiceImpl implements FileService{
	
	@Autowired
	FileMapper filemapper;

	@Override
	public int fileInsert(FileVO filevo) {
		return filemapper.fileInsert(filevo);
	}

	@Override
	public List<FileVO> fileSelect(int bno) {
		return filemapper.fileSelect(bno);
	}
	
	
}
