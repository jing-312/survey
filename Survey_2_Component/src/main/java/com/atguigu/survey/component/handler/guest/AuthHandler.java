package com.atguigu.survey.component.handler.guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.atguigu.survey.component.service.i.AuthService;

@Controller
public class AuthHandler {

	@Autowired
	private AuthService authService;
	
}
