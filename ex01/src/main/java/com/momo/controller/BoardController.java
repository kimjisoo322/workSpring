package com.momo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.momo.service.BoardService;
import com.momo.vo.BoardVO;

import lombok.extern.log4j.Log4j;

//controller는 페이지 분기 (어떤 페이지로 이동)

@Log4j
@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@GetMapping("list")
	public void getList(Model model) {
		List<BoardVO> list = boardService.getListXML();
		System.out.println("====================목록 출력!============");
		log.info(list);
		model.addAttribute("list", list);
	
	}
	
	@GetMapping("view")
	public void getOne(int bno, Model model) {
		BoardVO boardBno =	boardService.getOne(bno);
		System.out.println("===================한 건 조 회 !============");
		log.info(boardBno);
		model.addAttribute("boardBno", boardBno);	
	}
	
	@GetMapping("write")
	public void write(BoardVO board, Model model) {
		board = new BoardVO();
		
		board.setTitle(board.getTitle());
		board.setContent(board.getContent());
		board.setWriter(board.getWriter());
		
		model.addAttribute("board", board);
		
	}
	// void => /board/write 
	// return "write";  => web-inf/views/write.jsp
	@PostMapping("write")
	public String writeAction(BoardVO board ,Model model) {

		int res = boardService.insertXML(board);
		
		model.addAttribute("res",res);
		
		// 등록 후 다시 목록 페이지로 
		return "redirect:/board/list";
	}
	
}





