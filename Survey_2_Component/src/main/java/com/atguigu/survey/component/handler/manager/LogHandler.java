package com.atguigu.survey.component.handler.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.atguigu.survey.component.service.i.LogService;

@Controller
public class LogHandler {

	@Autowired
	private LogService logService;
}
