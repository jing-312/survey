package com.atguigu.survey.component.handler.guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.atguigu.survey.component.service.i.ResService;

@Controller
public class ResHandler {

	@Autowired
	private ResService resService;
}
