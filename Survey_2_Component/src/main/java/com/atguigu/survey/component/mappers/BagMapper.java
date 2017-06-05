package com.atguigu.survey.component.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.atguigu.survey.entities.guest.Bag;

public interface BagMapper {
	
    int deleteByPrimaryKey(Integer bagId);

    int insert(Bag record);
    
    int updateByPrimaryKey(Bag record);
    
    void updateBagOrder(Integer bagId);
    
    void deleteBagBySurveyId(Integer surveyId);
    
    void batchUpdateBagOrder(@Param("bagList") List<Bag> bagList);
    
    List<Bag> getBagListBySurveyId(Integer surveyId);
    
    Bag selectByPrimaryKey(Integer bagId);
    
    List<Bag> selectAll();

	void updateRelationshipByMove(
			@Param("bagId")Integer bagId,
			@Param("surveyId")Integer surveyId);

	Bag getDpeelyBagById(Integer bagId);
	
}