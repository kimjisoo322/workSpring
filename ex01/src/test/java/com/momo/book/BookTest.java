package com.momo.book;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.momo.board.BoardTest;
import com.momo.mapper.BookMapper;
import com.momo.vo.Book;
import com.momo.vo.Criteria;

import jdk.internal.org.jline.utils.Log;
import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BookTest {
	
	@Autowired
	BookMapper bookMapper;
	
	/*
	 * @Test public void bookList() { System.out.println("도서관 목록"); List<Book> list
	 * = bookMapper.bookList(null,null);
	 * 
	 * log.info(list); }
	 */
	/*
	 * @Test public void totalCnt() { System.out.println("총 건수 조회"); int res =
	 * bookMapper.totalCnt();
	 * 
	 * log.info("총 건수 : " + res); }
	 */
	
	@Test
	public void getOne() {
		System.out.println("한건 조회");
		Book book = bookMapper.getOne(62);
		log.info("한 건 : " + book);
	}
}
