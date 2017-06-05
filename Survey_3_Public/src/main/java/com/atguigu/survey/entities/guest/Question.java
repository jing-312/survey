package com.atguigu.survey.entities.guest;

import java.io.Serializable;
import java.util.List;

import com.atguigu.survey.utils.DataprocessUtils;

public class Question implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer questionId;

    private String questionName;

    private Integer questionType;

    private String questionOption;

    private Integer bagId;
    
    public Question() {
    	
	}

    public Question(Integer questionId, String questionName, Integer questionType, String questionOption,
			Integer bagId) {
		super();
		this.questionId = questionId;
		this.questionName = questionName;
		this.questionType = questionType;
		this.questionOption = questionOption;
		this.bagId = bagId;
	}

	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", questionName=" + questionName + ", questionType="
				+ questionType + ", questionOption=" + questionOption + ", bagId=" + bagId + "]";
	}

	public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName == null ? null : questionName.trim();
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public String getQuestionOption() {
        return questionOption;
    }

    public void setQuestionOption(String questionOption) {
        this.questionOption = questionOption == null ? null : questionOption.trim();
    }

    public Integer getBagId() {
        return bagId;
    }

    public void setBagId(Integer bagId) {
        this.bagId = bagId;
    }
    
    public List<String> getOptionList() {
    	return DataprocessUtils.jsonToList(questionOption);
    }
}