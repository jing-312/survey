package com.atguigu.survey.entities.manager;

public class Log {
    private Integer logId;

    private String operator;

    private String operateTime;

    private String methodName;

    private String methodType;

    private String inputData;

    private String outputData;

    private String exceptionType;

    private String exceptionMessage;

    
    public Log(Integer logId, String operator, String operateTime, String methodName, String methodType,
			String inputData, String outputData, String exceptionType, String exceptionMessage) {
		super();
		this.logId = logId;
		this.operator = operator;
		this.operateTime = operateTime;
		this.methodName = methodName;
		this.methodType = methodType;
		this.inputData = inputData;
		this.outputData = outputData;
		this.exceptionType = exceptionType;
		this.exceptionMessage = exceptionMessage;
	}

	public Log() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Log [logId=" + logId + ", operator=" + operator + ", operateTime=" + operateTime + ", methodName="
				+ methodName + ", methodType=" + methodType + ", inputData=" + inputData + ", outputData=" + outputData
				+ ", exceptionType=" + exceptionType + ", exceptionMessage=" + exceptionMessage + "]";
	}

	public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime == null ? null : operateTime.trim();
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName == null ? null : methodName.trim();
    }

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType == null ? null : methodType.trim();
    }

    public String getInputData() {
        return inputData;
    }

    public void setInputData(String inputData) {
        this.inputData = inputData == null ? null : inputData.trim();
    }

    public String getOutputData() {
        return outputData;
    }

    public void setOutputData(String outputData) {
        this.outputData = outputData == null ? null : outputData.trim();
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType == null ? null : exceptionType.trim();
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage == null ? null : exceptionMessage.trim();
    }
}