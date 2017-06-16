package com.atguigu.survey.component.handler.manager;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.survey.component.service.i.LogService;
import com.atguigu.survey.entities.manager.Log;
import com.atguigu.survey.log.aspects.RoutingKeybinder;
import com.atguigu.survey.page.Page;
import com.atguigu.survey.utils.GlobaleNames;

@Controller
public class LogHandler {

	@Autowired
	private LogService logService;
	
/*	@RequestMapping("/manager/log/showList")
	public String showList(@RequestParam(value="pageNoStr",required=false) String pageNoStr,Map<String,Object> map ){
		
		RoutingKeybinder.bindKey(RoutingKeybinder.DATASOURCE_LOG);
		
		Page<Log> page = logService.getPage(pageNoStr);
		
		map.put(GlobaleNames.PAGE, page);
		
		return "manager/log_showList";
		
	}*/
	
	/*@RequestMapping("/manager/log/showList")
	public String showList(Map<String, Object> map, @RequestParam(value="pageNoStr",required=false) String pageNoStr) {
		
		RoutingKeybinder.bindKey(RoutingKeybinder.DATASOURCE_LOG);
		Page<Log> page = logService.getPage(pageNoStr);
		
		map.put(GlobaleNames.PAGE, page);
		
		return "manager/log_showList";
	}*/
	
}
