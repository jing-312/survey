package com.atguigu.survey.component.service.i;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jfree.chart.JFreeChart;

import com.atguigu.survey.entities.guest.Survey;
import com.atguigu.survey.page.Page;

public interface StatisticsService {

	Page<Survey> getAllAvailablePage(String pageNoStr);

	Survey getSurveyDeeply(Integer surveyId);

	List<String> getListResult(Integer questionId);

	JFreeChart  getChart(Integer questionId);
	
	HSSFWorkbook getWorkBook(Integer surveyId);
}
