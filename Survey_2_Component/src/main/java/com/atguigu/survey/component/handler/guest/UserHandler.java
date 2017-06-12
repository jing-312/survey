package com.atguigu.survey.component.handler.guest;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.survey.component.service.i.UserService;
import com.atguigu.survey.entities.guest.User;
import com.atguigu.survey.utils.GlobaleNames;


@Controller
public class UserHandler {

	@Autowired
	private UserService userService;

	/**
	 * 用户注册系统方法，增加了权限之后的方法。
	 * @return
	 */
	@RequestMapping("/guest/user/regist")
	public String regist(User user){
		
		userService.regist(user);
		
		return "guest/user_loginUI";
	}
	
	
	/**
	 * 用户登录系统。
	 * @param user
	 * @param session
	 * @return
	 */
	@RequestMapping("/guest/user/login")
	public String userLongin(User user,HttpSession session){
		//用来判断用户名是否已经登录。
		User loginUser = userService.login(user);
		//把用户信息设置到session域中，以便于前段显示欢迎使用。
		session.setAttribute(GlobaleNames.LOGIN_USER, loginUser);
		//从定向到主页
		return  "redirect:/index.jsp";
				
		
	}
	/**
	 * 用户退出登录系统
	 * @param session
	 * @return
	 */
	@RequestMapping("/guest/user/logout")
	public String tologout(HttpSession session){
		
		session.removeAttribute(GlobaleNames.LOGIN_USER);
		
		return "redirect:/index.jsp";
	}

}
