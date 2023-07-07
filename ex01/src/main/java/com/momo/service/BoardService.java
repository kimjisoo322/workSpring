package com.momo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.momo.vo.BoardVO;
import com.momo.vo.Criteria;
import com.momo.vo.Member;

@Service
public interface BoardService {
	
	// 리스트 조회 + 검색 조건 추가
	public List<BoardVO> getListXML(Criteria criteria, Model model);
	
	// 멤버 조회 
	public List<Member> getListMem();
	
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
	public int getTotalCnt(Criteria criteria);
	


}
