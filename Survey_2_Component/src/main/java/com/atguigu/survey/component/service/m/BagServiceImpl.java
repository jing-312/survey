package com.atguigu.survey.component.service.m;


import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.component.mappers.BagMapper;
import com.atguigu.survey.component.mappers.QuestionMapper;
import com.atguigu.survey.component.service.i.BagService;
import com.atguigu.survey.entities.guest.Bag;
import com.atguigu.survey.entities.guest.Question;
import com.atguigu.survey.utils.DataprocessUtils;

@Service
public class BagServiceImpl implements BagService {
	@Autowired
	private BagMapper bagMapper;
	@Autowired
	private QuestionMapper  questionMapper;
	
	public void saveBag(Bag bag) {
		
		bagMapper.insert(bag);
		
		Integer bagId = bag.getBagId();
		
		bagMapper.updateBagOrder(bagId);
	}

	public void deleteBag(Integer bagId) {
		
		bagMapper.deleteByPrimaryKey(bagId);
		
	}

	public Bag selectBagById(Integer bagId) {
		
		return bagMapper.selectByPrimaryKey(bagId);
	}

	public void updateBagById(Bag bag) {
		bagMapper.updateByPrimaryKey(bag);
	}

	public void deleteDeeplyBag(Integer bagId) {
		
		//先删除包裹内的所有问题。
		questionMapper.deleteByBagId(bagId);
		//再删除包裹本身。
		
		bagMapper.deleteByPrimaryKey(bagId);
	}

	public List<Bag> getBagListBySurveyId(Integer surveyId) {
		
		return bagMapper.getBagListBySurveyId(surveyId);
	}

	public void batchUpdateBagOrder(List<Integer> bagIdList, List<Integer> bagOrderList) {
		
		List<Bag> bagList = new ArrayList<Bag>();
		for(int i = 0; i < bagIdList.size(); i++) {
			Integer bagId = bagIdList.get(i);
			Integer bagOrder = bagOrderList.get(i);
			Bag bag = new Bag(bagId, null, bagOrder, null);
			bagList.add(bag);
		}
		
		bagMapper.batchUpdateBagOrder(bagList);
	}

	public void updateRelationshipByMove(Integer bagId, Integer surveyId) {
		
		bagMapper.updateRelationshipByMove(bagId,surveyId);
		
	}

	public void updateRelationshipByCopy(Integer bagId, Integer surveyId) {
		
		//1.根据bagId深度加载Bag对象
		Bag source = bagMapper.getDpeelyBagById(bagId);
		//2.执行深度复制操作
		Bag target = (Bag) DataprocessUtils.deeplyCopy(source);
		//3.使用目标调查的surveyId设置Bag对象的对应属性
		target.setSurveyId(surveyId);
		//4.保存Bag对象
		bagMapper.insert(target);
		//5.批量保存Bag对象级联的Question对象
		LinkedHashSet<Question> questionSet = target.getQuestionSet();
		
		questionMapper.batchSaveQuestion(questionSet,target.getBagId());
	}


	
}
