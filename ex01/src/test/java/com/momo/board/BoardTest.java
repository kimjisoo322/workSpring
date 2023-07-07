package com.momo.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.momo.mapper.BoardMapper;
import com.momo.vo.BoardVO;
import com.momo.vo.Criteria;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardTest {
	// boardMapper 생성  -> boardMapper에는 xml에서 정의한 쿼리를 메서드로 생성해둠 
	@Autowired
	BoardMapper boardMapper;

	@Test
	public void getList() {
		assertNotNull(boardMapper);
		List<BoardVO> list = boardMapper.getList();
		
		// 반복문) list로 부터 꺼내온 값을 board에 저장
		list.forEach(board ->{
			log.info("boardVO ========");
			log.info(board.getBno());
			log.info(board.getTitle());
			log.info(board.getContent());
		});
	}
	
	// boardMapper.xml 테스트 
	@Test
	public void getListXML() {
		List<BoardVO> list = boardMapper.getListXML(null);
		
		// 반복문) list로 부터 꺼내온 값을 board에 저장
		list.forEach(board ->{
			log.info("boardVO ========");
			log.info(board.getBno());
			log.info(board.getTitle());
			log.info(board.getContent());
		});
	}
	
	@Test
	public void insertXML() {
		BoardVO board = new BoardVO();
		
		board.setTitle("새로운 제목");
		board.setContent("새로운 내용");
		board.setWriter("userJS");
		
		int res = boardMapper.insertXML(board);
		assertEquals(res, 1);
		
		log.info(board);
		log.info(boardMapper.insertXML(board) + "건 등록되었습니다.");
	}
	
	@Test
	public void insertSelectKey() {
		BoardVO board = new BoardVO();
		
		board.setTitle("제목selectKey");
		board.setContent("내용selectKey");
		board.setWriter("userJK");
		
		int res = boardMapper.insertSelectKey(board);
		assertEquals(res, 1);
		
		System.out.println("=======================");
		// bno가 가진 값을 보기 위해서 
		log.info("bno : " + board.getBno());
	}
	
	@Test
	public void getOne() {
		BoardVO board = boardMapper.getOne(23);
		System.out.println("============게시물 한건 조회=========");
		
		log.info(board);
		
	}
	
	@Test
	public void delete() {
		int res = boardMapper.delete(24);
		System.out.println("==========게시물 삭제========");
		
		log.info(res);
	}
	
	@Test
	public void updateXML() {

		BoardVO board =  boardMapper.getOne(28);
		board.setTitle("수정) 제목");
		board.setContent("수정) 내용");
		board.setWriter("수정) userJIN");
		System.out.println("==================게시물 수정===========");
		int res = boardMapper.updateXML(board);
		log.info(res);
	}
	
	@Test
	public void getTotalCnt() {
		int res = boardMapper.getTotalCnt();
		System.out.println("==================총건수 조회===========");
		log.info(res);
	}
	
}








