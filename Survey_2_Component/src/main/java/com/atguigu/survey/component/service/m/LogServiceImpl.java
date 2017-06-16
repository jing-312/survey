package com.atguigu.survey.component.service.m;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.component.mappers.LogMapper;
import com.atguigu.survey.component.service.i.LogService;
import com.atguigu.survey.entities.manager.Log;
import com.atguigu.survey.page.Page;
import com.atguigu.survey.utils.DataprocessUtils;

@Service
public class LogServiceImpl implements LogService {

	@Autowired
	private LogMapper logMapper;

	public void saveLog(Log log) {
		
		String tableName = DataprocessUtils.generateTableName(0);
		//logMapper.insert(log);
		//把数据保存到当月的数据表中
		logMapper.saveLogIntoCurrentMonth(log,tableName);
	}

	public void createTable(String tableName) {
		
		logMapper.createTable(tableName);
	}

	public Page<Log> getPage(String pageNoStr) {
		
		List<String> tableNames = logMapper.getTableNames();
		
		int totalRecordNo = logMapper.getTotalRecordNo(tableNames);
		
		Page<Log> page = new Page<Log>(pageNoStr, totalRecordNo);
		
		int index = page.getIndex();
		int pageSize = page.getPageSize();
		
		List<Log> list = logMapper.getPageList(index, pageSize, tableNames);
		
		page.setList(list);
		
		return page;
	}
}
