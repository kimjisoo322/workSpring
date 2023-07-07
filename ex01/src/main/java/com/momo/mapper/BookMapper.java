package com.momo.mapper;

import java.util.List;

import com.momo.vo.Book;
import com.momo.vo.Criteria;

public interface BookMapper {
	
	// book 리스트 조회
	 public List<Book> bookList(Criteria criteria);
	 
	 
	 // book 총 건수 조회 
	 public int totalCnt(Criteria criteria);
	 
	 // 상세보기 
	 public Book getOne(int no);

	 // 등록하기 (no값 가져오기)
	public int insertKey(Book book);
}
