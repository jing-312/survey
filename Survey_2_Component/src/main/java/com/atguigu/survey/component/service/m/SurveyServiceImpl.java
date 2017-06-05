package com.atguigu.survey.component.service.m;


import java.util.LinkedHashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.component.mappers.BagMapper;
import com.atguigu.survey.component.mappers.QuestionMapper;
import com.atguigu.survey.component.mappers.SurveyMapper;
import com.atguigu.survey.component.service.i.SurveyService;
import com.atguigu.survey.e.BagEmptyException;
import com.atguigu.survey.e.SurveyEmptyException;
import com.atguigu.survey.entities.guest.Bag;
import com.atguigu.survey.entities.guest.Question;
import com.atguigu.survey.entities.guest.Survey;
import com.atguigu.survey.page.Page;
import com.atguigu.survey.utils.GlobaleMessage;


@Service
public class SurveyServiceImpl implements SurveyService {

	@Autowired
	private SurveyMapper surveyMapper;
	
	@Autowired
	private BagMapper bagMapper;
	
	@Autowired
	private QuestionMapper questionMapper;
	/**
	 * 增加调查的具体实现方法。
	 */
	public void saveSurvey(Survey survey) {
		surveyMapper.insert(survey);
	}

	
	/**
	 * 处理显示我未完成的survey
	 */
	public Page<Survey> getMyUncompletedPage(Integer userId, String pageNoStr) {
		//1.查询数据库得到总记录数，
		int totalRecordNo = surveyMapper.getMyUncompletedCount(userId);
		//2.创建page对象
		Page<Survey> page = new Page<Survey>(pageNoStr, totalRecordNo);
		//3.获取pageSize和index
		int index = page.getIndex();
		int pageSize = page.getPageSize();
		//4.查询list
		List<Survey> list = surveyMapper.getMyUncompletedList(userId,index,pageSize);
		//将得到的list设置到page中，
		page.setList(list);
		
		return page;
	}
	
	/**
	 * 根据surveyId删除survey
	 */
	public void deleteSurvey(Integer surveyId) {
		surveyMapper.deleteByPrimaryKey(surveyId);
	}
	
	/**
	 * 根据surveyId查询survey
	 */
	public Survey selectSurvey(Integer surveyId) {
		return surveyMapper.selectByPrimaryKey(surveyId);
	}


	public void updateSurvey(Survey survey) {
		
		surveyMapper.updateByPrimaryKey(survey);
	}


	public Survey getSurveyDeeply(Integer surveyId) {
		// TODO Auto-generated method stub
		return surveyMapper.getSurveyDeeply(surveyId);
	}


	public void updateCompleteStatus(Integer surveyId) {
		
		//深度加载对象
		Survey survey = surveyMapper.getSurveyDeeply(surveyId);
		
		LinkedHashSet<Bag> bagSet = survey.getBagSet();
		
		int bagSize = bagSet.size();
		
		if(bagSize == 0){
			
			throw new SurveyEmptyException(GlobaleMessage.SURVEY_EMPTY);
		}
		
		for (Bag bag : bagSet) {
			
			LinkedHashSet<Question> questionSet = bag.getQuestionSet();
			
			int questionSize = questionSet.size();
			
			if(questionSize == 0){
				
				throw new BagEmptyException(GlobaleMessage.BAG_EMPTY);
			}
		}
		
		surveyMapper.updateCompleteStatus(surveyId);
		
	}


	public void deleteDeeplySurvey(Integer surveyId) {
		//先删除调查内的所有问题。
		questionMapper.deleteQuestionBySurveyId(surveyId);
		//再删除调查内的所有包裹。
		bagMapper.deleteBagBySurveyId(surveyId);
		//再删除调查本身。
		surveyMapper.deleteByPrimaryKey(surveyId);
	}


	
}
