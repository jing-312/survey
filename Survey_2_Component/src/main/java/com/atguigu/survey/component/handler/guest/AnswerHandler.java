package com.atguigu.survey.component.handler.guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.atguigu.survey.component.service.i.AnswerService;

@Controller
public class AnswerHandler {
	
	@SuppressWarnings("unused")
	@Autowired
	private AnswerService answerService;
}

