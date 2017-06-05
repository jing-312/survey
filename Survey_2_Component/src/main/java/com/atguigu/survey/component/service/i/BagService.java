package com.atguigu.survey.component.service.i;

import java.util.List;

import com.atguigu.survey.entities.guest.Bag;

public interface BagService {

	void saveBag(Bag bag);

	void deleteBag(Integer bagId);

	Bag selectBagById(Integer bagId);

	void updateBagById(Bag bag);

	void deleteDeeplyBag(Integer bagId);

	List<Bag> getBagListBySurveyId(Integer surveyId);

	void batchUpdateBagOrder(List<Integer> bagIdList, List<Integer> bagOrderList);

	void updateRelationshipByMove(Integer bagId, Integer surveyId);

	void updateRelationshipByCopy(Integer bagId, Integer surveyId);


}
