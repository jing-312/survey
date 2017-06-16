package com.atguigu.survey.component.service.i;

import com.atguigu.survey.entities.manager.Log;
import com.atguigu.survey.page.Page;

public interface LogService {

	void saveLog(Log log);

	void createTable(String tableName);

	Page<Log> getPage(String pageNoStr);
	
}
