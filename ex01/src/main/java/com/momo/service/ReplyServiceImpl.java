package com.momo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.momo.mapper.ReplyMapper;
import com.momo.vo.Criteria;
import com.momo.vo.ReplyVO;


@Service
public class ReplyServiceImpl implements ReplyService{
	
	@Autowired
	ReplyMapper replyMapper;

	@Override
	public List<ReplyVO> getList(int bno, Criteria criteria) {
		return replyMapper.getList(bno, criteria);
	}

	@Override
	public int insertReply(ReplyVO reply) {
		return replyMapper.insertReply(reply);
	}

	@Override
	public int deleteReply(int rno) {
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
