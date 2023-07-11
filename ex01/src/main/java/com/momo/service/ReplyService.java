package com.momo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.momo.vo.Criteria;
import com.momo.vo.ReplyVO;

@Service
public interface ReplyService {
	
	// 댓글 조회
	public List<ReplyVO> getList(int bno, Criteria criteria);

	// 댓글 작성 
	public int insertReply(ReplyVO reply);
	
	// 댓글 삭제 
	public int deleteReply(int rno);
	
	// 댓글 수정 
	public int updateReply(ReplyVO reply);
	
	// 댓글 한 건 조회 
	public ReplyVO getOne(int rno);

	// 총 건수 조회 
	public int totalCnt(int bno);
}
