package com.momo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.momo.vo.BoardVO;
import com.momo.vo.Criteria;
import com.momo.vo.Member;

public interface BoardMapper {
	
	@Select("select * from tbl_board")
	public List<BoardVO> getList();
	
	// 리스트 조회 + 검색 조건 추가
	public List<BoardVO> getListXML(Criteria criteria);
	
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
	public int getTotalCnt();

	public int getTotalCnt(Criteria criteria);
	
	// 댓글 수 증가 (트랜잭션 처리)
	/**
	 * 	파라메터가 2개 이상인 경우 param 어노테이션 달아주기! 
	 * */
	public int updateReplyCnt(@Param("bno")int bno, @Param("amount")int amount);
	
	
}	
