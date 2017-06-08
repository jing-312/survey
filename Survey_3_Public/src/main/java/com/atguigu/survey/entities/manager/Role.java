package com.atguigu.survey.entities.manager;

import java.util.Set;

public class Role {
    private Integer roleId;

    private String roleName;

    private Set<Auth> authSet;
    
    public Set<Auth> getAuthSet() {
		return authSet;
	}

	public void setAuthSet(Set<Auth> authSet) {
		this.authSet = authSet;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + "]";
	}

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(Integer roleId, String roleName) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
	}

	public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }
}