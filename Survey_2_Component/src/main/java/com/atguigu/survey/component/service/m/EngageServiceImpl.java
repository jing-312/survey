package com.atguigu.survey.component.service.m;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.component.mappers.SurveyMapper;
import com.atguigu.survey.component.mappers.AnswerMapper;
import com.atguigu.survey.component.service.i.EngageService;
import com.atguigu.survey.entities.guest.Answer;
import com.atguigu.survey.entities.guest.Survey;
import com.atguigu.survey.page.Page;
import com.atguigu.survey.utils.DataprocessUtils;


@Service
public class EngageServiceImpl implements EngageService {

	@Autowired
	private SurveyMapper surveyMapper;
	@Autowired
	private AnswerMapper answerMapper;
	
	public Page<Survey> getAllAvailable(String pageNoStr) {
		//1.查询总记录数：已完成
		int totalRecordNo = surveyMapper.getAllAvailableCount();
		//2.创建Page对象
		Page<Survey> page = new Page<Survey>(pageNoStr, totalRecordNo);
		//3.获取index和pageSize
		int index = page.getIndex();
		int pageSize = page.getPageSize();
		//4.根据index和pageSize查询List集合
		List<Survey> list = surveyMapper.getAllAvailableList(index,pageSize);
		//5.将list集合设置到Page对象中
		page.setList(list);
		//6.将Page对象返回
		return page;
	}


	public Survey getSurveyById(Integer surveyId) {
		
		return surveyMapper.getSurveyDeeply(surveyId);
	}


	public void saveAfterParseAnswers(Map<Integer, Map<String, String[]>> allBagMap, Integer surveyId) {
		//先生成UUID唯一标识码
		String answerUuid = UUID.randomUUID().toString();
		//1.创建List集合
		List<Answer> answerList = new ArrayList<Answer>();
		//解析答案数据与包裹没有关系，所以只获取allBagMap的value部分即可，
		Collection<Map<String, String[]>> values = allBagMap.values();
		//遍历allBag的Value部分
		for (Map<String, String[]> paramMap : values) {
			Set<String> keySet = paramMap.keySet();
			for (String key : keySet) {
				//检查key是否是以q开头，如果不是则停止本次循环。
				if(!key.startsWith("q")){
					continue;
				}
				//创建answer对象并保存数据到answerList中
				String[] value = paramMap.get(key);
				String answerContent = DataprocessUtils.generateAnswerContent(value);
				
				Integer questionId = DataprocessUtils.parseQuestionId(key);
				
				Answer answer = new Answer(null, answerContent, answerUuid, questionId, surveyId);
				
				answerList.add(answer);
			}
			
		}
		answerMapper.batchSaveAnswers(answerList);
	}
}
