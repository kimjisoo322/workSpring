package com.momo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
public class FileController {
	
	@Autowired
	FileService fileservice;
	
	@GetMapping("/file/fileupload")
	public void fileupload() {
		
	}
							// upload인 파일 경로를 ATTACHES_DIR 상수에 저장 
	private static final String ATTACHES_DIR ="c:\\upload\\";
	
	/*
	 * 1 ) 전달된 파일이 없는 경우 -> nullpoint 예외 발생! 
	 * 2 ) enctype 의 오타 > 오류 발생!
	 * 3 ) 설정 오류 > MultipartFile 사용하기 위해 pom.xml 라이브러리 설정 + root-context bean 설정 
	 * */
	
	@PostMapping("/file/fileuploadAction")
	public String fileuploadAction(ArrayList<MultipartFile> files, int bno, RedirectAttributes rttr) {
		
		// insert한 결과를 담는다. 
		int insertRes = 0;
		
			//  files을  반복하면서  file에 저장하여 출력함 
			for(MultipartFile file : files) {
				
				// 파일이 비어있으면 밑줄 실행하지 하고 다시 올라옴
				if(file.isEmpty()) {
					continue;
				}
					log.info(" ==== 파일업로드 ====" );
					log.info("oname : " + file.getOriginalFilename());
					log.info("name : " + file.getName());
					log.info("size : " + file.getSize());
				
				try {
					/**
					 *  소프트웨어 구축에 쓰이는 식별자 표준
					 *  파일이름이 중복되어 파일이 소실되지 않도록 UUID를 붙여서 저장
					 * */
					UUID uuid =UUID.randomUUID();
					// 파일 이름 (소실방지)  	      식별자 표준 + _ + 파일 원본이름
					String saveFileName =  uuid + "_" + file.getOriginalFilename();

					// dir > c:/upload/2023/7/18  >  년/월/일 
					String uploadPath = getFolder();
					
											// getFolder()는 2023\07\18\ 반환
					File sfile = new File(ATTACHES_DIR + getFolder() + saveFileName);
					
					// 사용자로부터 받아온 file(원본파일) sfile(저장된파일명)에 저장   
					file.transferTo(sfile);
					
					FileVO filevo = new FileVO();
					
					// 주어진 파일의 Mime유형을 확인
					String contentType = Files.probeContentType(sfile.toPath());
					
					// Mime 타입을 확인하여 이미지인경우 썸네일을 생성
						if(contentType != null && contentType.startsWith("image")) {
							
							// contetType이 image일 경우, I 
							filevo.setFiletype("I");
							
							String thmbnail = ATTACHES_DIR + uploadPath + "s_" + saveFileName;
							
							// 원본파일(썸네일을 만들 이미지 파일) : sfile / 크키 / thmbnail : 경로 
							Thumbnails.of(sfile).size(100, 100).toFile(thmbnail);
						}else {
							filevo.setFiletype("F");
						}
					
					// file의 정보를 등록 
					filevo.setBno(bno);
					filevo.setFilename(file.getOriginalFilename());
					filevo.setUploadpath(uploadPath);
					filevo.setUuid(uuid.toString());
					
					int res = fileservice.fileInsert(filevo);
					
					// res의 결과가 0보다 크다면, insertRes의 결과를 증가시킴 
					if(res>0) {
						insertRes++;
					}
					
				} catch (IllegalStateException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
			String message = insertRes + "건 저장되었습니다.";
			rttr.addAttribute("message", message);
		return "redirect:/file/fileupload";
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

	// 경로 생성 (중복방지용)
    // 업로드 날짜를 폴더 이름으로 사용
	public String getFolder() {
		// 날짜 가져오기 
		LocalDate currentDate = LocalDate.now();
		String uploadPath = currentDate.toString().replace("-", File.separator) + File.separator;

		log.info("날짜 : " + currentDate);
		log.info("uploadPth : " + currentDate.toString().replace("-", File.separator) + File.separator );
		
		// 폴더 생성  			 C:\\upload\2023\07\18
		File saveDir = new File(ATTACHES_DIR + uploadPath);
		 // (폴더가 없으면) 
		if(!saveDir.exists()) {
			// 여러개 's' mkdir과 mkdirs 
			if(saveDir.mkdirs()) {
				log.info("완료) 폴더 생성");
			}else {
				log.info("실패) 폴더생성"); 
			}
		}
		return uploadPath;
	}
	
	/*
	 * public static void main(String[] args) { 
	 * LocalDate currentDate = LocalDate.now(); 
	 * String uploadPath = currentDate.toString().replace("-",File.separator); 
	 * log.info("날짜 : " + currentDate); log.info("uploadPth : "+uploadPath);
	 * 
	 * }
	 */
}


















