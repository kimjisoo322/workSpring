package com.momo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.momo.vo.Criteria;
import com.momo.vo.ReplyVO;

public interface ReplyMapper {
	
	// 댓글 조회하기 (게시판 번호를 파라메터로 받는다)
	/**
	 * 	매개변수가 두 개 이상 전달되는 경우
	 *  @param 어노테이션 사용
	 *  @param bno
	 *  @param criteria 
	 * */
	public List<ReplyVO> getList(@Param(value ="bno")int bno, @Param(value="criteria") Criteria criteria);
	
	// 댓글 작성하기 
	public int insertReply(ReplyVO reply);
	
	// 댓글 삭제하기 
	public int deleteReply(int rno);
	
	// 댓글 수정하기 
	public int updateReply(ReplyVO reply);
	
	// 댓글 한 건 조회 
	public ReplyVO getOne(int rno);
	
	// 댓글 총 건수 조회 
	public int totalCnt(int bno);
	
}
