package com.momo.reply;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.momo.service.ReplyService;
import com.momo.vo.Criteria;
import com.momo.vo.ReplyVO;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ReplyServiceTest {
	
	@Autowired
	ReplyService replyService;
	
	@Test
	public void test() {
		Criteria criteria = new Criteria();
		criteria.setAmount(5);
		criteria.setPageNo(1);
		List<ReplyVO> list = replyService.getList(84, criteria);
		log.info("서비스) 댓글 조회 =========" + list);
	}
	@Test
	public void insertTest() {
		ReplyVO reply = new ReplyVO();
		reply.setBno(84);
		reply.setReply(" 댓글) 서비스 테스트용");
		reply.setReplyer(" 작성자userV");
		
		int res  = replyService.insertReply(reply);
		log.info("=========댓글 등록========");
		log.info(res);
	}
	
	@Test
	public void deleteTest() {
		int res = replyService.deleteReply(17);
		log.info("===========댓글 삭제=======");
		log.info(res);
	}
	@Test
	public void updateTest() {
		ReplyVO reply= replyService.getOne(12);
		reply.setReply("댓 수정");
		int res = replyService.updateReply(reply);
		log.info(res);
	}
}
