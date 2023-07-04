package com.momo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.momo.vo.BoardVO;

public interface BoardMapper {
	
	@Select("select * from tbl_board")
	public List<BoardVO> getList();
	
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
