package com.momo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.momo.mapper.logMapper;
import com.momo.vo.logVo;

@Service
public class logServiceImpl implements logService {
	
	@Autowired
	logMapper logmapper;
	
	@Override
	public int logInsert(logVo logvo) {
		return logmapper.logInsert(logvo);
	}

}
