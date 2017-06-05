package com.atguigu.survey.component.service.m;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.component.mappers.QuestionMapper;
import com.atguigu.survey.component.service.i.QuestionService;
import com.atguigu.survey.entities.guest.Question;
@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionMapper questionMapper;

	public void saveQuestion(Question question) {
		questionMapper.insert(question);
	}

	public void deleteQuestion(Integer questionId) {
		questionMapper.deleteByPrimaryKey(questionId);
	}

	public Question seleteQuestion(Integer questionId) {
		// TODO Auto-generated method stub
		return questionMapper.selectByPrimaryKey(questionId);
	}

	public void updateQuestion(Question question) {
		
		questionMapper.updateByPrimaryKey(question);
	}


}
