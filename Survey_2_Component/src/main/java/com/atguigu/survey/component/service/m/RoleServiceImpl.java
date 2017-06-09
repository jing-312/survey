package com.atguigu.survey.component.service.m;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.component.mappers.AdminMapper;
import com.atguigu.survey.component.mappers.ResMapper;
import com.atguigu.survey.component.mappers.RoleMapper;
import com.atguigu.survey.component.mappers.UserMapper;
import com.atguigu.survey.component.service.i.RoleService;
import com.atguigu.survey.entities.guest.User;
import com.atguigu.survey.entities.manager.Admin;
import com.atguigu.survey.entities.manager.Role;
import com.atguigu.survey.utils.DataprocessUtils;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private ResMapper resMapper;
	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private UserMapper userMapper;
	
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
	
	public List<Role> getRoleList() {
		return roleMapper.selectAll();
	}
	public List<Integer> getCurrentAuthIdList(Integer roleId) {
		return roleMapper.getCurrentAuthIdList(roleId);
	}
	
	public void dispatcher(Integer roleId, List<Integer> authIdList) {
		
		roleMapper.removeOldRelationship(roleId);
		
		if(authIdList != null) {
			
			roleMapper.saveNewRelationship(roleId, authIdList);
		}
		
		Integer maxPos = resMapper.getSystemMaxPos();
		
		List<Admin> adminList = adminMapper.selectAll();
		
		for (Admin admin : adminList) {
			
			Integer adminId = admin.getAdminId();
			
			Set<Role> roleSet = adminMapper.getRoleSetDeeply(adminId);
			
			String codeArr = DataprocessUtils.calculateCodeArr(roleSet, maxPos);
			
			admin.setCodeArrStr(codeArr);
		}
		
		adminMapper.batchUpdateCodeArr(adminList);
		
		List<User> userList = userMapper.selectAll();
		
		for (User user : userList) {
			
			Boolean company = user.getCompany();
			
			String roleName = null;
			
			if(company) {
				roleName = "企业用户";
			}else{
				roleName = "个人用户";
			}
			
			Role role = roleMapper.getRoleDeeplyByName(roleName);
			
			Set<Role> roleSet = new HashSet<Role>();
			
			roleSet.add(role);
			
			String codeArr = DataprocessUtils.calculateCodeArr(roleSet, maxPos);
			
			user.setCodeArrStr(codeArr);
		}
		
		userMapper.batchUpdateCodeArr(userList);
	}
}
