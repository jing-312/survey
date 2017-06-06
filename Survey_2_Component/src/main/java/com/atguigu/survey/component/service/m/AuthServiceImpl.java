package com.atguigu.survey.component.service.m;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.component.mappers.AuthMapper;
import com.atguigu.survey.component.service.i.AuthService;

@Service
public class AuthServiceImpl implements AuthService{

	@Autowired
	private AuthMapper authMapper;
}
