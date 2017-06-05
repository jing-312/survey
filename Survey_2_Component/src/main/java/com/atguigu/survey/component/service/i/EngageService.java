package com.atguigu.survey.component.service.i;

import java.util.Map;

import com.atguigu.survey.entities.guest.Survey;
import com.atguigu.survey.page.Page;

public interface EngageService {

	Page<Survey> getAllAvailable(String pageNoStr);

	Survey getSurveyById(Integer surveyId);

	void saveAfterParseAnswers(Map<Integer, Map<String, String[]>> allBagMap, Integer surveyId);

}
