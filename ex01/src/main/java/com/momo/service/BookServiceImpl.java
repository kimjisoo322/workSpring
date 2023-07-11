package com.momo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.momo.mapper.BookMapper;
import com.momo.vo.Book;
import com.momo.vo.Criteria;
import com.momo.vo.PageDto;

import jdk.internal.org.jline.utils.Log;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class BookServiceImpl implements BookService{

	@Autowired
	BookMapper bookMapper;

	@Override
	public List<Book> bookList(Criteria criteria, Model model) {
		// mapper에서 조회할 때 번호를 얻어오기 위해 매개변수에 criteria 넣어줌
		
		/*
		 * 	1. 리스트 조회
		 *  2. 총 건수 조회
		 *  3. 페이지 DTO 생성 = 페이지 블럭 만드는 녀석
		 * */
		
		List<Book> list = bookMapper.bookList(criteria);
		int totalCnt = bookMapper.totalCnt(criteria);
		PageDto pageDto = new PageDto(criteria, totalCnt);
		
		model.addAttribute("list", list);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("pageDto", pageDto);
		model.addAttribute("criteria", criteria);
		
		return null;
	}
	// 총건수
	@Override
	public int totalCnt(Criteria criteria) {
		return bookMapper.totalCnt(criteria);
	}
	// 상세보기 
	@Override
	public Book getOne(int no, Model model) {
		Book book= bookMapper.getOne(no);
		model.addAttribute("book", book);
		return book;
	}

	// 등록하기 
	@Override
	public String insertKey(Book book, Model model, RedirectAttributes rttr) {
		
		int res = bookMapper.insertKey(book);
		
		String message = "";
		log.info("=============res " + res);
		
		if(res > 0) {
			
			message = book.getNo() + "번 글이 등록되었습니다.";
			log.info(message);
			rttr.addFlashAttribute("message", message);
			return "redirect:/book/list";
		}else {
			message ="등록 오류! (service 확인)";
			model.addAttribute("message", message);
			return "/book/message";
		}
	}
	
	

}
