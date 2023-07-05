package com.momo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	@GetMapping("message")
	public void write(Model mode) {

	}

	@GetMapping("list_boot")
	public void list_boot(Model model) {
		model.addAttribute("list", boardService.getListXML());
	}

	@GetMapping("view")
	public void getOne(int bno, Model model) {

		System.out.println("===================한 건 조 회 !============");
		log.info("board");
		model.addAttribute("board", boardService.getOne(bno));
	}

	/**
	 * requestMapping에 /board/가 설정되어있으므로 /board/write 로 호출
	 */
	@GetMapping("write")
	public void write(BoardVO board, Model model) {

	}

	// void => /board/write
	// return "write"; => web-inf/views/write.jsp
	@PostMapping("write")
	public String writeAction(BoardVO board, Model model, RedirectAttributes rttr) {
		// board에 bno가 저장되어있음 (insertSelectKey) = 파라메터로 넘길 때 주소값을 가지고 있음
		int res = boardService.insertSelectKey(board);

		// redirect를 하게 되면 message가 유지가 안됨 (값을 가져가지 않음) -> redirect 하면서 값을 가져가고 싶을 때
		// RedirectAttributes (영역을 가져감)
		String message = "";
		if (res > 0) {
			message = board.getBno() + "번 글이 등록되었습니다.";
			// rttr.addAttribute("message", message); -> ${param.message}
			// 새로 고침하게 되면 메세지가 등록되지 않음 (세션저장)
			rttr.addFlashAttribute("message", message);
			return "redirect:/board/list_boot";
		} else {
			message = "등록 중 오류 발생하였습니다.";
			model.addAttribute("message", message);
			return "/board/message";
		}
	}
	
	
	@GetMapping("edit")
	public String updateXML(int bno, Model model) {
		System.out.println("===================한 건 조 회 !============");
		log.info("board");
		model.addAttribute("board", boardService.getOne(bno));
		return "/board/edit";
	}
	@PostMapping("updateAction")
	public String updateXML(BoardVO board, RedirectAttributes rttr, Model model) {

		
		 BoardVO bd = boardService.getOne(board.getBno());
		 
		 bd.setTitle(bd.getTitle()); 
		 bd.setContent(bd.getContent());
		 bd.setWriter(bd.getWriter());
		 
		int res = boardService.updateXML(board);
		log.info(res);

		String message = "";
		if (res > 0) {
			message = "수정되었습니다.";
			rttr.addFlashAttribute("message", message);
			return "redirect:/board/list_boot";
		} else {
			message = "수정 중 오류가 발생하였습니다.";
			model.addAttribute("message", message);
			return "/board/message";
		}
	}
	
	@GetMapping("deleteAction")
	public String delete(int bno, RedirectAttributes rttr, Model model) {
	
		int res = boardService.delete(bno);
		log.info("삭제" + bno);
		log.info("삭제 res : " + res);
		String message ="";
		if (res > 0) {
			message = "삭제되었습니다.";
			rttr.addFlashAttribute("message", message);
			return "redirect:/board/list_boot";
		}else {
			message = "삭제 중 오류 발생하였습니다.";
			model.addAttribute("message",message);
			return "/board/message";
		}
	}
}
