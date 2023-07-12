package com.momo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.momo.vo.Book;
import com.momo.vo.Criteria;

@Service
public interface BookService {
	
	// 구현부가 없는 {} 메서드 = 추상메서드
	public List<Book> bookList(Criteria criteria, Model model);
	
	// 총건수 조회
	public int totalCnt(Criteria criteria);
	
	// 한 건 조회 
	public Book getOne(int no, Model model);

	public int insertKey(Book book);



}
