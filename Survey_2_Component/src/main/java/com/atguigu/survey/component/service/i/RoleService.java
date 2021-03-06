package com.atguigu.survey.component.service.i;

import java.util.List;

import com.atguigu.survey.entities.manager.Role;

public interface RoleService {

	void savaRole(Role role);

	List<Role> getAllRole();

	void batchDelete(List<Integer> roleIdList);

	void updateRole(Role role);

	List<Role> getRoleList();

	List<Integer> getCurrentAuthIdList(Integer roleId);

	void dispatcher(Integer roleId, List<Integer> authList);


}
