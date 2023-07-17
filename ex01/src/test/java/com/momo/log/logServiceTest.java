package com.momo.log;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.momo.board.BoardServiceTest;
import com.momo.service.logService;
import com.momo.vo.logVo;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class logServiceTest {
	
	@Autowired
	logService logService;
	
	@Test
	public void logInsert() {
		logVo logvo = new logVo();
		logvo.setClassname("");
		logvo.setMethodname("");
		logvo.setParams("");
		logvo.setErrmsg("");
		logvo.getRegdate();
		int res = logService.logInsert(logvo);
		
		log.info(" 등록 : " + res);
		
	}
}
