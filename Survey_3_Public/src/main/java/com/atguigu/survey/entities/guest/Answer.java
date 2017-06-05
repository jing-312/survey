package com.atguigu.survey.entities.guest;

public class Answer {
    private Integer answerId;

    private String answerContent;

    private String answerUuid;

    private Integer questionId;

    private Integer surveyId;

    public Answer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Answer(Integer answerId, String answerContent, String answerUuid, Integer questionId, Integer surveyId) {
		super();
		this.answerId = answerId;
		this.answerContent = answerContent;
		this.answerUuid = answerUuid;
		this.questionId = questionId;
		this.surveyId = surveyId;
	}

	@Override
	public String toString() {
		return "Answer [answerId=" + answerId + ", answerContent=" + answerContent + ", answerUuid=" + answerUuid
				+ ", questionId=" + questionId + ", surveyId=" + surveyId + "]";
	}

	public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent == null ? null : answerContent.trim();
    }

    public String getAnswerUuid() {
        return answerUuid;
    }

    public void setAnswerUuid(String answerUuid) {
        this.answerUuid = answerUuid == null ? null : answerUuid.trim();
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Integer surveyId) {
        this.surveyId = surveyId;
    }
}