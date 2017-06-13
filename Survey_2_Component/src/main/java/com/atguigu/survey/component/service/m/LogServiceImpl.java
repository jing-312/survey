package com.atguigu.survey.component.service.m;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.component.mappers.LogMapper;
import com.atguigu.survey.component.service.i.LogService;
import com.atguigu.survey.entities.manager.Log;

@Service
public class LogServiceImpl implements LogService {

	@Autowired
	private LogMapper logMapper;

	public void saveLog(Log log) {
		
		logMapper.insert(log);
	}
}
