package com.atguigu.survey.entities.manager;

public class Auth {
    private Integer authId;

    private String authName;

    
    public Auth(Integer authId, String authName) {
		super();
		this.authId = authId;
		this.authName = authName;
	}

	public Auth() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Auth [authId=" + authId + ", authName=" + authName + "]";
	}

	public Integer getAuthId() {
        return authId;
    }

    public void setAuthId(Integer authId) {
        this.authId = authId;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName == null ? null : authName.trim();
    }
}