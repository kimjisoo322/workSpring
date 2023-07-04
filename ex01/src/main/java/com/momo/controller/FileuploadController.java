package com.momo.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class FileuploadController {
	
	/*
	 * 	메서드의 리턴타입 
	 * 
	 *  String 
	 *  	/WEB-INF/views/반환값.jsp 응답페이지 주소 
	 *  	servlet-context.xml에 정의되어있음 
	 *  
	 *  void : 요청주소와 동일한 이름의 jsp를 반환 
	 *   		/ 내가 요청한 경로 / .jsp
	 * */
	
	// WEB-INF > VIEWS > FILE 폴더 > FILEUPLOAD
	@GetMapping("/file/fileupload")
	public void fileupload( Model model) {
		model.addAttribute("message", "파일을 업로드 해주세요 ☺");
		System.out.println("파일업로드");
	}
	
	/**
	 * 파일업로드용 라이브러리 추가 
	 * commons-fileupload
	 * 
	 * cos.jar와 달리 파일을 저장하는 로직이 추가되어야 함!
	 * 
	 * 1. 라이브러리 추가 
	 * 2. multipartResolver 빈 등록 
	 * 3. 메서드의 매개변수로  MultipartFile 이용 
	 * 
	 * @param mr
	 * 
	 * */
	@PostMapping("/file/fileupload")
	public void fileuploadAction(ArrayList<MultipartFile> files, Model model) {
		
		/*
		 * for(MultipartFile file:files) {
		 *?
		 */
		
		files.forEach(file ->{
			System.out.println("============ 파일 업로드 🎞 ===========");
			System.out.println("oname : " + file.getOriginalFilename());
			System.out.println("name : " + file.getName());
			System.out.println("size : " + file.getSize());
			
		});
		
		model.addAttribute("message", "파일 업로드가 완료되었습니다.☺");
	}
	
}



