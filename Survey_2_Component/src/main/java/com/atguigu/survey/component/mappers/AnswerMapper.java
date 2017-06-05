package com.atguigu.survey.component.mappers;

import com.atguigu.survey.entities.guest.Answer;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface AnswerMapper {
    int deleteByPrimaryKey(Integer answerId);

    int insert(Answer record);

    Answer selectByPrimaryKey(Integer answerId);

    List<Answer> selectAll();

    int updateByPrimaryKey(Answer record);

	void batchSaveAnswers(@Param("answerList")List<Answer> answerList);

	List<String> getListResult(Integer questionId);

	int getQuestionEngagedCount(Integer questionId);

	int getOptionEngagedCount(
			@Param("questionId")Integer questionId,
			@Param("indexParam") String indexParam);

	int getSurveyEngagedCount(Integer surveyId);

	List<Answer> getAnswerListBySurveyId(Integer surveyId);
}