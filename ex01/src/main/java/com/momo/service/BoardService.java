package com.momo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.momo.vo.BoardVO;

@Service
public interface BoardService {
	
	public List<BoardVO> getListXML();
	
	// INSERT, UPDATE, DELETE 반환 = int 
	public int insertXML(BoardVO board);
	
	public int insertSelectKey(BoardVO board);
	
	// 게시물 조회 (한건) = 상세보기
	public BoardVO getOne(int bno);
	
	// 게시물 삭제하기 
	public int delete(int bno);
	
	// 게시물 수정하기 
	public int updateXML(BoardVO board);
	
	// 총 건수 조회 
	public int getTotalCnt();

}
