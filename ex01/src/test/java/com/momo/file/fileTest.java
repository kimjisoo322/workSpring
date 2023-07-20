package com.momo.file;

import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.momo.mapper.FileMapper;
import com.momo.vo.FileVO;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class fileTest {
	
	@Autowired
	FileMapper filemapper;
	
	@Test
	public void insertTest() {
		FileVO filevo = new FileVO();
		filevo.setUploadpath("1");
		filevo.setFilename("1");
		filevo.setFiletype("1");
		
		UUID uuid = UUID.randomUUID();
		filevo.setUuid(uuid.toString());
		
		filevo.setBno(85);
		
		int res = filemapper.fileInsert(filevo);
		log.info(res);
	}
	
	@Test
	public void selectTest() {
		List<FileVO> list= filemapper.fileSelect(85);
		
		list.forEach(file ->{
			file.getBno();
			file.getUuid();
			file.getFilename();
			file.getFiletype();
			file.getUploadpath();
			log.info(file);
		});
	}
	@Test
	public void deleteTest() {
		int res =  filemapper.fileDelete(85, "51459de4-cf8d-460a-816a-124a71cc9685");
		log.info("파일삭제");
		log.info(res);
		
	}
}
