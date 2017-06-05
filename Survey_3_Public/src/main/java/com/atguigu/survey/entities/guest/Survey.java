package com.atguigu.survey.entities.guest;


import java.util.LinkedHashSet;

public class Survey {
    private Integer surveyId;

    private String surveyName;

    private String logoPath ="surveyLogos/logo.gif";

    private Boolean completed = false;

    private Integer userId;
    
    private LinkedHashSet<Bag> bagSet;


	public LinkedHashSet<Bag> getBagSet() {
		return bagSet;
	}

	public void setBagSet(LinkedHashSet<Bag> bagSet) {
		this.bagSet = bagSet;
	}

	public Survey(Integer surveyId, String surveyName, String logoPath, Boolean completed, Integer userId) {
		super();
		this.surveyId = surveyId;
		this.surveyName = surveyName;
		this.logoPath = logoPath;
		this.completed = completed;
		this.userId = userId;
	}

	public Survey() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Survey [surveyId=" + surveyId + ", surveyName=" + surveyName + ", logoPath=" + logoPath + ", completed="
				+ completed + ", userId=" + userId + "]";
	}

	public Integer getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Integer surveyId) {
        this.surveyId = surveyId;
    }

    public String getSurveyName() {
        return surveyName;
    }

    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName == null ? null : surveyName.trim();
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath == null ? null : logoPath.trim();
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}