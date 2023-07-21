package com.momo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.momo.vo.FileVO;

@Service
public interface FileService {	
	
	// 등록
	public int fileInsert(FileVO filevo);
	
	// 조회
	public List<FileVO> fileSelect(int bno);
	
	// 파일 삭제 
	public int fileDelete(@Param("bno")int bno, @Param("uuid")String uuid);
	
											// fileServiceImpl에서 오류 예외처리를 하고 있기 때문에 똑같이 맞춰줌
											//			(✨impl을 수정하면 service도 똑같이 맞춰주기!)
	public int fileupload(List<MultipartFile> files, int bno) throws Exception;
	
}
