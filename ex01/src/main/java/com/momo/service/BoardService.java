package com.momo.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.momo.vo.BoardVO;
import com.momo.vo.Criteria;
import com.momo.vo.Member;

@Service
public interface BoardService {
	
	// 리스트 조회 + 검색 조건 추가
	public List<BoardVO> getListXML(Criteria criteria, Model model);
	
	// 멤버 조회 
	public List<Member> getListMem();
	
	// INSERT, UPDATE, DELETE 반환 = int 
	public int insertXML(BoardVO board);
										// fileServiceImpl에서 오류 예외처리를 하고 ->boardServiceImpl에서 오류 예외처리를 해서  똑같이 맞춰줌
	 									//							(✨impl을 수정하면 service도 똑같이 맞춰주기!)
	public int insertSelectKey(BoardVO board, ArrayList<MultipartFile> files) throws Exception;
	
	// 게시물 조회 (한건) = 상세보기
	public BoardVO getOne(int bno);
	
	// 게시물 삭제하기 
	public int delete(int bno);
	
	// 게시물 수정하기 
	public int updateXML(BoardVO board, ArrayList<MultipartFile> files) throws Exception;
	
	// 총 건수 조회 
	public int getTotalCnt(Criteria criteria);
	
	// 댓글 수 증가 
	public int updateReplyCnt(@Param("bno") int bno, @Param("amount") int amount);

}
