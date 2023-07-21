package com.momo.controller;

import java.awt.PageAttributes.MediaType;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.print.attribute.standard.Media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.momo.service.FileService;
import com.momo.vo.FileVO;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnails;

@Log4j
@Controller
public class FileController extends CommonRestController{
	
	@Autowired
	FileService fileservice;
	
	// upload인 파일 경로를 ATTACHES_DIR 상수에 저장 
	public static final String ATTACHES_DIR ="c:\\upload\\";

	
	@GetMapping("/file/fileupload")
	public void fileupload() {
		
	}
	
	/*
	 * 1 ) 전달된 파일이 없는 경우 -> nullpoint 예외 발생! 
	 * 2 ) enctype 의 오타 > 오류 발생!
	 * 3 ) 설정 오류 > MultipartFile 사용하기 위해 pom.xml 라이브러리 설정 + root-context bean 설정 
	 * */
	
	@PostMapping("/file/fileuploadAction")
	public String fileuploadAction(ArrayList<MultipartFile> files, int bno, RedirectAttributes rttr) throws Exception {
		
			int insertRes= fileservice.fileupload(files, bno);

			String message = insertRes + "건 저장되었습니다.";
			rttr.addAttribute("message", message);
		return "redirect:/file/fileupload";
	}
	
	// fetch 버전 ( post ) 
	@PostMapping("/file/fileuploadActionFetch")
	public @ResponseBody Map<String, Object> fileuploadAction(ArrayList<MultipartFile> files, int bno) throws Exception {
			
		log.info( "fetch action 실행 : " + "fileuploadActionFetch");
		
		int insertRes= fileservice.fileupload(files, bno);
		log.info( "업로드 건수 : " + insertRes );
		
		// CommonRestController에서 map 생성한 것 가져와서 씀!! = responseMap 
		return responseMap("success", insertRes + "건 저장되었습니다.");
	}
	/**
	 * 	List 조회 
	 * */
	@GetMapping("/file/list/{bno}")
	public @ResponseBody Map<String, Object> fileuploadList(@PathVariable("bno") int bno) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", fileservice.fileSelect(bno));
		return map; 
	}


	
	/*
	 * public static void main(String[] args) { 
	 * LocalDate currentDate = LocalDate.now(); 
	 * String uploadPath = currentDate.toString().replace("-",File.separator); 
	 * log.info("날짜 : " + currentDate); log.info("uploadPth : "+uploadPath);
	 * 
	 * }
	 */
	

	@GetMapping("/file/delete/{bno}/{uuid}")
	public @ResponseBody Map<String, Object> delete (@PathVariable("bno")int bno, @PathVariable("uuid")String uuid){
		
		int res = fileservice.fileDelete(bno, uuid);
		
		log.info( "파일 삭제 건수 : " + res );
		if(res > 0) {
			log.info("들어왔나요..? " +res );
			return responseDeleteMap(res);
			
		}else {
			return responseDeleteMap(res);
		}
	}
	// 파일 다운로드 
	/**
	 *  컨텐츠 타입을 다운로드 받을 수 있는 형식으로 지정하여 
	 *  브라우저에서 파일을 다운로드 할 수 있게 처리
	 *   ( 파일 요청 할 때 파일 이름을 매개변수로 넣어줌 )
	 *   * HttpStatus : 응답결과를 코드로 전달
	 * */
	@GetMapping("/file/download")	
	public ResponseEntity<byte[]> download(String fileName){
		log.info("download File : " + fileName);
		
		// HttpHeaders 는 org.springframework로 import! 
		HttpHeaders headers = new HttpHeaders();
		File file = new File(ATTACHES_DIR + fileName);
		
		if(file.exists()) {
			// 컨텐츠 타입을 지정   
			// APPLICATION_OCTET_STREAM : 이진 파일의 콘텐츠 유형 
			headers.add("contentType", org.springframework.http.MediaType.APPLICATION_OCTET_STREAM.toString());;
			
			// 컨텐츠에 대한 추가 설명 및 파일 이름 한글 처리
			try {
				headers.add("Content-Disposition", "attachment; filename=\""
				+ new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
				
				return new ResponseEntity<>(
						// 파일 , 헤더 , 결과 코드 
						FileCopyUtils.copyToByteArray(file)
						, headers
						, HttpStatus.OK
						);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			} catch (IOException e) {
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}else {		
		return new ResponseEntity<>(HttpStatus.OK);
		}
	}
}	
