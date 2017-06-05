package com.atguigu.survey.component.mappers;

import com.atguigu.survey.entities.guest.Question;

import java.util.LinkedHashSet;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface QuestionMapper {
	
    int deleteByPrimaryKey(Integer questionId);

    int insert(Question record);

    Question selectByPrimaryKey(Integer questionId);

    List<Question> selectAll();

    int updateByPrimaryKey(Question record);

	void deleteByBagId(Integer bagId);

	void deleteQuestionBySurveyId(Integer surveyId);

	void batchSaveQuestion(
			@Param("questionSet")LinkedHashSet<Question> questionSet,
			@Param("bagId")Integer bagId);
}