package com.atguigu.survey.entities.manager;

import java.util.Set;

public class Auth {
    private Integer authId;

    private String authName;
    
    private Set<Res> resSet;
    
    public Auth() {
		
	}

    public Auth(Integer authId, String authName) {
		super();
		this.authId = authId;
		this.authName = authName;
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
    
    public Set<Res> getResSet() {
		return resSet;
	}
    
    public void setResSet(Set<Res> resSet) {
		this.resSet = resSet;
	}
}