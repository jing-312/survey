package com.atguigu.survey.component.service.i;

import com.atguigu.survey.entities.guest.User;

public interface UserService {

	User login(User user);

	void regist(User user);

}
