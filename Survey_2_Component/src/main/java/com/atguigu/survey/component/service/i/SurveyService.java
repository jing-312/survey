package com.atguigu.survey.component.service.i;

import com.atguigu.survey.entities.guest.Survey;
import com.atguigu.survey.page.Page;

public interface SurveyService {

	void saveSurvey(Survey survey);

	Page<Survey> getMyUncompletedPage(Integer userId, String pageNoStr);

	void deleteSurvey(Integer surveyId);
	
	Survey selectSurvey(Integer surveyId);

	void updateSurvey(Survey survey);

	Survey getSurveyDeeply(Integer surveyId);

	void updateCompleteStatus(Integer surveyId);

	void deleteDeeplySurvey(Integer surveyId);

	

}
