package com.atguigu.survey.log.aspects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.atguigu.survey.component.service.i.LogService;
import com.atguigu.survey.utils.DataprocessUtils;

public class CreateLogTableListener implements ApplicationListener<ContextRefreshedEvent> {
	
	@Autowired
	private LogService logService;

	
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		//1.创建日志表的表名
		String tableName = DataprocessUtils.generateTableName(0);
		
		//2.创建日志表
		logService.createTable(tableName);
		
		tableName = DataprocessUtils.generateTableName(1);
		logService.createTable(tableName);

		
		tableName = DataprocessUtils.generateTableName(2);
		logService.createTable(tableName);
		
		tableName = DataprocessUtils.generateTableName(3);
		logService.createTable(tableName);
	}

}
