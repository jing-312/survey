package com.atguigu.survey.component.service.i;

import com.atguigu.survey.entities.guest.Question;

public interface QuestionService {

	void saveQuestion(Question question);

	void deleteQuestion(Integer questionId);

	Question seleteQuestion(Integer questionId);

	void updateQuestion(Question question);

}
