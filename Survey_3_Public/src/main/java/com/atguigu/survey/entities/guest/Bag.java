package com.atguigu.survey.entities.guest;

import java.io.Serializable;
import java.util.LinkedHashSet;

public class Bag implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer bagId;

    private String bagName;

    private Integer bagOrder;

    private Integer surveyId;
    
    private LinkedHashSet<Question> questionSet;
    
    public LinkedHashSet<Question> getQuestionSet() {
		return questionSet;
	}

	public void setQuestionSet(LinkedHashSet<Question> questionSet) {
		this.questionSet = questionSet;
	}

	public Bag() {
		// TODO Auto-generated constructor stub
	}

    public Bag(Integer bagId, String bagName, Integer bagOrder, Integer surveyId) {
		super();
		this.bagId = bagId;
		this.bagName = bagName;
		this.bagOrder = bagOrder;
		this.surveyId = surveyId;
	}

	@Override
	public String toString() {
		return "Bag [bagId=" + bagId + ", bagName=" + bagName + ", bagOrder=" + bagOrder + ", surveyId=" + surveyId
				+ "]";
	}

	public Integer getBagId() {
        return bagId;
    }

    public void setBagId(Integer bagId) {
        this.bagId = bagId;
    }

    public String getBagName() {
        return bagName;
    }

    public void setBagName(String bagName) {
        this.bagName = bagName == null ? null : bagName.trim();
    }

    public Integer getBagOrder() {
        return bagOrder;
    }

    public void setBagOrder(Integer bagOrder) {
        this.bagOrder = bagOrder;
    }

    public Integer getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Integer surveyId) {
        this.surveyId = surveyId;
    }


    
}