package com.atguigu.survey.component.handler.guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.atguigu.survey.component.service.i.RoleService;

@Controller
public class RoleHandler {

	@Autowired
	private RoleService roleService;
}
