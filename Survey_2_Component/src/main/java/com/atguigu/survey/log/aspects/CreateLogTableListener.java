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
		RoutingKeybinder.bindKey(RoutingKeybinder.DATASOURCE_LOG);
		logService.createTable(tableName);
		
		tableName = DataprocessUtils.generateTableName(1);
		RoutingKeybinder.bindKey(RoutingKeybinder.DATASOURCE_LOG);
		logService.createTable(tableName);

		
		tableName = DataprocessUtils.generateTableName(2);
		RoutingKeybinder.bindKey(RoutingKeybinder.DATASOURCE_LOG);
		logService.createTable(tableName);
		
		tableName = DataprocessUtils.generateTableName(3);
		RoutingKeybinder.bindKey(RoutingKeybinder.DATASOURCE_LOG);
		logService.createTable(tableName);
	}

}
