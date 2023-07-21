package com.momo.board;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.momo.service.BoardService;
import com.momo.vo.BoardVO;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardServiceTest {
	
	@Autowired
	BoardService boardService;
	
	@Test
	public void getListXML() {
		List<BoardVO> list = boardService.getListXML(null, null);
		
		list.forEach(board-> {
			log.info(board.getBno());
			log.info(board);
		});
	}
	
	@Test
	public void getOne() {
		BoardVO board = boardService.getOne(1);
		log.info(board);
	}
	@Test
	public void updateXML() {

		BoardVO board =  boardService.getOne(72);
		board.setTitle("수정) 제목");
		board.setContent("수정) 내용");
		board.setWriter("수정) userJIN");
		System.out.println("==================게시물 수정===========");
		//int res = boardService.updateXML(board);
		//log.info(res);
	}

	@Test
	public void delete() {
		int res =boardService.delete(64);
		System.out.println("==========게시물 삭제========");
		
		log.info(res);
	}
	
	// 댓글 수 증가 테스트 
	@Test
	public void  updateReplyCnt() {
		int res = boardService.updateReplyCnt(62, 1);
		assertEquals(1, res);
	}
	
	
}
