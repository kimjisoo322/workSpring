package com.momo.reply;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.momo.board.BoardTest;
import com.momo.mapper.ReplyMapper;
import com.momo.vo.Criteria;
import com.momo.vo.ReplyVO;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ReplyTest {
	
	@Autowired
	ReplyMapper replyMapper;
	
	@Test
	public void test() {
		assertNotNull(replyMapper);
		// 페이지 번호 , 게시물 수 
		Criteria criteria = new Criteria();
		criteria.setAmount(5);
		criteria.setPageNo(1);
		
		List<ReplyVO> list = replyMapper.getList(84, criteria);
		
		log.info("================");
		log.info("댓글 조회" + list);
	}
	
	@Test 
	public void totalCnt() { 
		System.out.println("총 건수 조회"); 
		int res = replyMapper.totalCnt(74);
		log.info("총 건수 : " + res);
	}
	
	@Test
	public void insertTest() {
		ReplyVO reply = new ReplyVO();
		reply.setBno(84);
		reply.setReply("댓) 테스트용");
		reply.setReplyer("작성자userJK");
		int res = replyMapper.insertReply(reply);
		assertEquals(res, 1);
	}
	
	@Test
	public void deleteTest() {
		int res = replyMapper.deleteReply(17);
		assertEquals(res, 1);
	}
	@Test
	public void getOneTest() {
		ReplyVO reply = replyMapper.getOne(20);
		log.info(reply);
	}
	
	@Test
	public void updateTest() {
		ReplyVO reply = replyMapper.getOne(27);
		reply.setReply("댓글 수정해봅니다!");
		reply.setReplyer("userJS");
		int res =  replyMapper.updateReply(reply);
		assertEquals(res, 1);
	}
}
