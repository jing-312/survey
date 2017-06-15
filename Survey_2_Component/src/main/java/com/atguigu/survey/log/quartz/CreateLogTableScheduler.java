package com.atguigu.survey.log.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.atguigu.survey.component.service.i.LogService;
import com.atguigu.survey.log.aspects.RoutingKeybinder;
import com.atguigu.survey.utils.DataprocessUtils;

public class CreateLogTableScheduler extends QuartzJobBean{

	@Autowired
	private LogService logService;
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		
		String tableName = DataprocessUtils.generateTableName(1);
		RoutingKeybinder.bindKey(RoutingKeybinder.DATASOURCE_LOG);
		logService.createTable(tableName);
		
		tableName = DataprocessUtils.generateTableName(2);
		RoutingKeybinder.bindKey(RoutingKeybinder.DATASOURCE_LOG);
		logService.createTable(tableName);
		
		tableName = DataprocessUtils.generateTableName(3);
		RoutingKeybinder.bindKey(RoutingKeybinder.DATASOURCE_LOG);
		logService.createTable(tableName);
	}

	
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
}
