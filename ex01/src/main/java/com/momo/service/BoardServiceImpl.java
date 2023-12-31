package com.momo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.momo.mapper.BoardMapper;
import com.momo.vo.BoardVO;
import com.momo.vo.Criteria;
import com.momo.vo.Member;
import com.momo.vo.PageDto;

import jdk.internal.org.jline.utils.Log;
import lombok.extern.log4j.Log4j;

/**
 * 	각 계층간의 연결은 인터페이스를 활용하여 느슨한 결합을 한다. 
 * 	
 *	★ Service : 계층 구조 상 비즈니스 영역을 담당하는 객체임을 표시 
 *         ★ root-context.xml > component-scan 속성에 패키지 등록
 *     예) <context:component-scan base-package="com.momo.service"></context:component-scan>
 * */

/*
 * 	서비스를 interface로 생성하는 이유
 *  1. 내부로직의 분리 
 *  2. 구현체의 전환이 용이
 *  3. 테스트 용이성 
 * */
@Log4j
@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
	@Autowired
	private FileService fileservice;
	
	// 리스트 조회 + 검색 조건 추가 
	@Override
	public List<BoardVO> getListXML(Criteria criteria, Model model) {
		/*
		 * 	1. 리스트 조회 (검색어, 페이지 정보 - startNo ~ endNo까지 조회) 
		 *  2. 총 건수 조회
		 *  3. pageDto 객체 생성 
		============> service에서 다 조회해서 다 저장함 
		 * */
		
		List<BoardVO> list = boardMapper.getListXML(criteria);
									//
		int totalCnt = boardMapper.getTotalCnt(criteria);
		PageDto pageDto = new PageDto(criteria, totalCnt);
		
		log.info("cri : " + criteria);
		log.info("list : "+ list);
		
		model.addAttribute("list", list);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("pageDto", pageDto);
		model.addAttribute("criteria", criteria);
		
		return null;
	}
	

	@Override
	public int insertXML(BoardVO board) {
		return boardMapper.insertXML(board);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int insertSelectKey(BoardVO board, ArrayList<MultipartFile> files) throws Exception {
		
		// 게시물 등록
		int res = boardMapper.insertSelectKey(board);
		
		// 파일 첨부
		fileservice.fileupload(files, board.getBno());
		return res;
	}

	@Override
	public BoardVO getOne(int bno) {
		return boardMapper.getOne(bno);
	}

	@Override
	public int delete(int bno) {
		// 게시물 삭제 시 첨부된 파일이 있는 경우 오류 발생
		// 첨부파일을 모두 삭제
		// 첨부파일 리스트 조회 ->fileuploadService
		// 리스트를 돌면서 삭제 처리 - fileuploadService    ) 또는 제약조건 없애기
		return boardMapper.delete(bno);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int updateXML(BoardVO board, ArrayList<MultipartFile> files) throws Exception {
		int res = boardMapper.updateXML(board);
		
		fileservice.fileupload(files, board.getBno());		
		return res;
	}

	@Override
	public List<Member> getListMem() {
		return boardMapper.getListMem();
	}


	@Override
	public int getTotalCnt(Criteria criteria) {
		return boardMapper.getTotalCnt(criteria);
	}


	@Override
	public int updateReplyCnt(int bno, int amount) {
		return boardMapper.updateReplyCnt(bno, amount);
	}


}
