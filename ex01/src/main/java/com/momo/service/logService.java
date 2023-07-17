package com.momo.service;

import org.springframework.stereotype.Service;

import com.momo.vo.logVo;

@Service
public interface logService {
	
	public int logInsert(logVo logvo);

}
