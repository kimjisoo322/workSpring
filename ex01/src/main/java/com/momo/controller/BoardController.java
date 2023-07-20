package com.momo.controller;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.momo.service.BoardService;
import com.momo.service.FileService;
import com.momo.vo.BoardVO;
import com.momo.vo.Criteria;

import lombok.extern.log4j.Log4j;

//controller는 페이지 분기 (어떤 페이지로 이동)

@Log4j
@Controller
@RequestMapping("/board/*")
public class BoardController extends FileController {

	@Autowired
	BoardService boardService;
	
	@Autowired
	FileService fileservice;
	
	@GetMapping("login")
	public void login() {
		
	}
	
	@GetMapping("/reply/test")
	public String test() {
		
		 // reply/test.jsp 를 반환
		return "reply/test";
	}
	
	@GetMapping("message")
	public void write(Model model) {

	}
	@GetMapping("SearchForm")
	public void wirte() {
		
	}

	@GetMapping("memlist")
	public void list_mem(Model model) {
		model.addAttribute("memlist", boardService.getListMem()) ;
	}
	
	/**	
	 * 	파라메터 자동 수집 (컨트롤러)
	 *  : 기본 생성자가 있어야 함 , 기본 생성자를 이용해서 객체를 생성  => setter메서드를 이용해서 생성
	 * */
	@GetMapping("list_boot")
	public void list_boot(Model model, Criteria criteria) {
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		boardService.getListXML(criteria, model);
		
		log.info("=================list");
		log.info("cri : " + criteria);
		
		stopWatch.stop();
		log.info(" 수행시간 : " + stopWatch.getTotalTimeMillis()+ "(ms)초");
		
		//model.addAttribute("list",boardService.getListXML(criteria, model); );
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
	@PostMapping("writeAction")
	public String writeAction(BoardVO board, Model model, RedirectAttributes rttr, ArrayList<MultipartFile> files) {
	
	
		// board에 bno가 저장되어있음 (insertSelectKey) = 파라메터로 넘길 때 주소값을 가지고 있음
		int res = boardService.insertSelectKey(board);
		
		fileupload(files,board.getBno());
		
/**	 파일첨부
 * */
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

	/*
	 * 	수정하기 
	 *     - bno를 파라메터로 받아야 함 
	 *     - 버튼의 액션이 달라짐
	 * */
	@GetMapping("edit")
	public String updateXML(int bno, Model model) {
		System.out.println("===================한 건 조 회 !============");
		log.info("board");
		model.addAttribute("board", boardService.getOne(bno));
		return "/board/edit";
	}
	
	@PostMapping("updateAction")
	public String updateXML(BoardVO board, RedirectAttributes rttr, Model model, Criteria criteria) {
		
		// request.getParam("pageNo") 
		// request.setAttribute("")
		// addAttribute
	//	=> 컨트롤러가 자동 수집해줌 (매개변수에 적어주면) => pageNo = 1 (쿼리스트링, get방식) = ${param.pageNo}
	
		// request.getAttribute('') 
		// session.setAttribute 
		// addFlashAttribute
	//  => request 내장 객체에 저장 => ${pageNo}
		
		 BoardVO bd = boardService.getOne(board.getBno());
		 
		 bd.setTitle(bd.getTitle()); 
		 bd.setContent(bd.getContent());
		 bd.setWriter(bd.getWriter());
		 
		 boardService.getListXML(criteria, model);
		 
		int res = boardService.updateXML(board);
		log.info(res);

		String message = "";
		if (res > 0) {
			message = res + "건 수정되었습니다.";
			/**
			 * 	 [ 검색을 한 상태에서 수정하기를 했을 때, 수정이 완료되면 다시 그 페이지로 유지 하게 되는 것 ]  
			 * */
			rttr.addAttribute("pageNo", criteria.getPageNo());
			rttr.addAttribute("searchField", criteria.getSearchField());
			rttr.addAttribute("searchWorld", criteria.getSearchWorld());
			rttr.addFlashAttribute("message", message); // ${message}
		//	 rttr.addAttribute("message", message);					// ${param.message}
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
