package com.atguigu.survey.entities.guest;

public class User {
    private Integer userId;

    private String userName;

    private String userPwd;

    private Boolean company;
    
    
    private String CodeArrStr;

    public String getCodeArrStr() {
		return CodeArrStr;
	}

	public void setCodeArrStr(String codeArrStr) {
		CodeArrStr = codeArrStr;
	}

	public User() {
		super();
	}

	public User(Integer userId, String userName, String userPwd, Boolean company) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPwd = userPwd;
		this.company = company;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPwd=" + userPwd + ", company=" + company
				+ "]";
	}

	public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd == null ? null : userPwd.trim();
    }

    public Boolean getCompany() {
        return company;
    }

    public void setCompany(Boolean company) {
        this.company = company;
    }
}