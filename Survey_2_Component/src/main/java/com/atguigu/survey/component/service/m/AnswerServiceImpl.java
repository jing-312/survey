package com.atguigu.survey.component.service.m;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.component.mappers.AnswerMapper;
import com.atguigu.survey.component.service.i.AnswerService;

@Service
public class AnswerServiceImpl implements AnswerService {

	@SuppressWarnings("unused")
	@Autowired
	private AnswerMapper answerMapper;
	
}
