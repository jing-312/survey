package com.atguigu.survey.component.mappers;

import com.atguigu.survey.entities.guest.Survey;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SurveyMapper {
    int deleteByPrimaryKey(Integer surveyId);

    int insert(Survey record);

    Survey selectByPrimaryKey(Integer surveyId);

    List<Survey> selectAll();

    int updateByPrimaryKey(Survey record);

	int getMyUncompletedCount(Integer userId);

	List<Survey> getMyUncompletedList(
									@Param("userId")Integer userId,
									@Param("index") int index, 
									@Param("pageSize") int pageSize);
	Survey getSurveyDeeply (Integer surveyId);

	void updateCompleteStatus(Integer surveyId);

	int getAllAvailableCount();

	List<Survey> getAllAvailableList(
			@Param("index")int index, 
			@Param("pageSize")int pageSize);
}