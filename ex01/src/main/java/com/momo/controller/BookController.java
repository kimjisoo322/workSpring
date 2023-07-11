package com.momo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.momo.service.BookService;
import com.momo.vo.Book;
import com.momo.vo.Criteria;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/book/*")
public class BookController {
	
	@Autowired
	BookService bookService;
	
	
	@GetMapping("message")
	public void write(Model mode) {

	}
	
	//return "/book/list";
	//->  /WEB-INF/views /book/list.jsp
	@GetMapping("list")
	public void list(Criteria criteria,Model model) {
		
		// pageNo type = int ('' 공백 시 입력 오류)
		
		bookService.bookList(criteria,model);

		log.info("cri : " + criteria);
	
	}
	
	@GetMapping("view")
	public void getOne(int no, Model model) {
		
		bookService.getOne(no, model);
		
		log.info(bookService.getOne(no, model));
	}
	
	@GetMapping("write")
	public void write() {
		
	}

	// Controller의 역할 (= 페이지 전환  / return '페이지경로'; 이 페이지를 반환해줘! ) 
	@PostMapping("writeAction")
	public String writeAction(Book book, Model model, RedirectAttributes rttr) {
		return bookService.insertKey(book, model, rttr);
	}
}
