package com.atguigu.survey.component.service.m;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.component.mappers.RoleMapper;
import com.atguigu.survey.component.service.i.RoleService;
import com.atguigu.survey.entities.manager.Role;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;

	public void savaRole(Role role) {
		roleMapper.insert(role);
		
	}
	public List<Role> getAllRole() {
		
		return roleMapper.selectAll();
	}
	public void batchDelete(List<Integer> roleIdList) {
		roleMapper.batchDelete(roleIdList);
	}
	public void updateRole(Role role) {
		roleMapper.updateByPrimaryKey(role);
	}
}
