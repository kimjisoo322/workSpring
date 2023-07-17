package com.momo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.momo.mapper.ReplyMapper;
import com.momo.vo.Criteria;
import com.momo.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService{
	
	@Autowired
	ReplyMapper replyMapper;

	@Autowired
	BoardService boardservice;
	
	@Override
	public List<ReplyVO> getList(int bno, Criteria criteria) {
		return replyMapper.getList(bno, criteria);
	}
	/** @Transactional
	 *   두개 다 동시에 성공하면 commit, 하나라도 실패하면 rollback
	 * */
	
	// 게시물 테이블에 있는 reply 건수를 올려주거나 내려주는 역할을 위해서 
	// 댓글이 등록되거나 삭제될 때 사용 
	@Transactional
	@Override
	public int insertReply(ReplyVO reply) {
		 // 댓글 입력시 게시물 테이블의 댓글 수를 1 증가 시켜줌 
		// 하나의 테이블에서 insert, update를 하고 있기 때문에 트랜잭션 처리★ 필요! 
		boardservice.updateReplyCnt(reply.getBno(), 1);
		return replyMapper.insertReply(reply);
	}
	@Transactional
	@Override
	// 총 3번의 트랜잭션 처리 실행 
	public int deleteReply(int rno) { 			
		ReplyVO reply = replyMapper.getOne(rno);  // 댓글 한건 조회 (1) > 댓글 삭제 (2)
		boardservice.updateReplyCnt(reply.getBno(), -1); // 댓글 삭제 후 replyCnt 감소 (3)  
		return replyMapper.deleteReply(rno);
	}

	@Override
	public ReplyVO getOne(int rno) {
		return replyMapper.getOne(rno);
	}

	@Override
	public int updateReply(ReplyVO reply) {
		return replyMapper.updateReply(reply);
	}

	@Override
	public int totalCnt(int bno) {
		return replyMapper.totalCnt(bno);
	}


}
