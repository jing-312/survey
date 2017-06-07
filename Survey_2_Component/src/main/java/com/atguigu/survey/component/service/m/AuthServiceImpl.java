package com.atguigu.survey.component.service.m;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.component.mappers.AuthMapper;
import com.atguigu.survey.component.service.i.AuthService;
import com.atguigu.survey.entities.manager.Auth;

@Service
public class AuthServiceImpl implements AuthService{

	@Autowired
	private AuthMapper authMapper;


	public void addAuth(Auth auth) {
		authMapper.insert(auth);
		
	}


	public List<Auth> getAllAuth() {
		
		return authMapper.selectAll();
	}


	public void batchDelete(List<Integer> authIdList) {
		
		authMapper.batchDelete(authIdList);
		
	}


	public void updateAuthName(Auth auth) {
	authMapper.updateByPrimaryKey(auth);
		
	}

}
