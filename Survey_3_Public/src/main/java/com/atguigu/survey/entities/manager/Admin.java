package com.atguigu.survey.entities.manager;

import java.util.Set;

public class Admin {
    private Integer adminId;

    private String adminName;

    private String adminPwd;
    
    private String CodeArrStr;

    private Set<Role> roleSet;

	public Set<Role> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(Set<Role> roleSet) {
		this.roleSet = roleSet;
	}

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(Integer adminId, String adminName, String adminPwd, String codeArrStr) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminPwd = adminPwd;
		CodeArrStr = codeArrStr;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminName=" + adminName + ", adminPwd=" + adminPwd + ", CodeArrStr="
				+ CodeArrStr + "]";
	}

	public String getCodeArrStr() {
		return CodeArrStr;
	}

	public void setCodeArrStr(String codeArrStr) {
		CodeArrStr = codeArrStr;
	}

	public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName == null ? null : adminName.trim();
    }

    public String getAdminPwd() {
        return adminPwd;
    }

    public void setAdminPwd(String adminPwd) {
        this.adminPwd = adminPwd == null ? null : adminPwd.trim();
    }
}