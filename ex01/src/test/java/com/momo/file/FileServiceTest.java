package com.momo.file;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.momo.service.FileService;
import com.momo.vo.FileVO;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class FileServiceTest {
	
	@Autowired
	FileService fileservice;
	
	@Test
	public void insertTest() {
		FileVO filevo = new FileVO();
		filevo.setBno(85);
		filevo.setFilename("2");
		filevo.setFiletype("2");
		filevo.setUploadpath("2");
		filevo.setUuid("2");
		
		int res = fileservice.fileInsert(filevo);
		log.info(res);
		assertEquals(res, 1);
	}
	
	@Test
	public void selectTest() {
		List<FileVO> list = fileservice.fileSelect(85);
		list.forEach(file ->{
			file.getBno();
			file.getFilename();
			file.getFiletype();
			file.getUploadpath();
			file.getUuid();
			log.info(file);
		});
	}
}
