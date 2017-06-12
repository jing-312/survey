package com.atguigu.survey.component.service.m;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.component.mappers.ResMapper;
import com.atguigu.survey.component.mappers.RoleMapper;
import com.atguigu.survey.component.mappers.UserMapper;
import com.atguigu.survey.component.service.i.UserService;
import com.atguigu.survey.e.UserLoginFailedException;
import com.atguigu.survey.e.UserNameAlreadyExistsException;
import com.atguigu.survey.entities.guest.User;
import com.atguigu.survey.entities.manager.Role;
import com.atguigu.survey.utils.DataprocessUtils;
import com.atguigu.survey.utils.GlobaleMessage;

@Service
public class UserServiceImpl implements UserService {

	@Autowired 
	private UserMapper userMapper;
	
	@Autowired 
	private RoleMapper roleMapper;
	
	@Autowired 
	private ResMapper resMapper;
	/**
	 * 实现用户登录功能。
	 */
	public User login(User user) {
		
		String userName = user.getUserName();
		String userPwd = user.getUserPwd();
		
		userPwd = DataprocessUtils.md5(userPwd);
		
		User loginUser =userMapper.getUserByLogin(userName,userPwd);
		
			if(loginUser == null){
				throw new UserLoginFailedException(GlobaleMessage.USER_LOGIN_FAILED);
			}
			
		return loginUser;
	}


	public void regist(User user) {
		String userName = user.getUserName();
		String userPwd = user.getUserPwd();
		
		int  count = userMapper.getUserCount(userName);
		
		if(count>0){
			throw new UserNameAlreadyExistsException(GlobaleMessage.USER_NAME_ALREADY_EXISTS);
		}
		
		user.setUserPwd(DataprocessUtils.md5(userPwd));
		
		String roleName = null;
		
		if(user.getCompany()){
			
			roleName = "企业用户";
		}else{
			
			roleName = "个人用户";
		}
		
		Role role = roleMapper.getRoleDeeplyByName(roleName);
		
		Set<Role> roleSet = new HashSet<Role>();
		roleSet.add(role);
		
		Integer maxPos = resMapper.getSystemMaxPos();
		
		String codeArrStr = DataprocessUtils.calculateCodeArr(roleSet, maxPos);
		
		user.setCodeArrStr(codeArrStr);
		
		userMapper.insert(user);
		
		Integer userId = user.getUserId();
		Integer roleId = role.getRoleId();
		
		userMapper.saveRelationShip(userId ,roleId);
	}
}
