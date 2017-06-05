package com.atguigu.survey.component.service.m;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.component.mappers.UserMapper;
import com.atguigu.survey.component.service.i.UserService;
import com.atguigu.survey.e.UserLoginFailedException;
import com.atguigu.survey.e.UserNameAlreadyExistsException;
import com.atguigu.survey.entities.guest.User;
import com.atguigu.survey.utils.DataprocessUtils;
import com.atguigu.survey.utils.GlobaleMessage;

@Service
public class UserServiceImpl implements UserService {

	@Autowired 
	private UserMapper userMapper;

	public void insert(User user) {
		String userName = user.getUserName();
		String userPwd = user.getUserPwd();
		
		int count = userMapper.getNameCount(userName);
		
		if(count>0){
			throw new UserNameAlreadyExistsException(GlobaleMessage.USER_NAME_ALREADY_EXISTS);
		}
		
		String md5 = DataprocessUtils.md5(userPwd);
		
		user.setUserPwd(md5);
		
		userMapper.insert(user);
	}

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
}
