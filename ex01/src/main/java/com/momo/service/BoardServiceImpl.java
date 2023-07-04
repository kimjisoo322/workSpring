package com.momo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.momo.mapper.BoardMapper;
import com.momo.vo.BoardVO;

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
@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public List<BoardVO> getListXML() {
		return boardMapper.getListXML();
	}

	@Override
	public int insertXML(BoardVO board) {
		return boardMapper.insertXML(board);
	}

	@Override
	public int insertSelectKey(BoardVO board) {
		return boardMapper.insertSelectKey(board);
	}

	@Override
	public BoardVO getOne(int bno) {
		return boardMapper.getOne(bno);
	}

	@Override
	public int delete(int bno) {
		return boardMapper.delete(bno);
	}

	@Override
	public int updateXML(BoardVO board) {
		return boardMapper.updateXML(board);
	}

	@Override
	public int getTotalCnt() {
		return boardMapper.getTotalCnt();
	}

}
