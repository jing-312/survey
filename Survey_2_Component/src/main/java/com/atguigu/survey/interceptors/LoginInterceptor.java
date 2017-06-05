package com.atguigu.survey.interceptors;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import com.atguigu.survey.e.AdminOperationForbiddenException;
import com.atguigu.survey.e.UserOperationForbiddenException;
import com.atguigu.survey.entities.guest.User;
import com.atguigu.survey.entities.manager.Admin;
import com.atguigu.survey.utils.GlobaleMessage;
import com.atguigu.survey.utils.GlobaleNames;
/**
 * 自定义拦截器的实现与学习。
 * @author Administrator
 *
 */

public class LoginInterceptor extends  HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		
		//先排除静态资源，静态资源不进行拦截。
		if(handler instanceof DefaultServletHttpRequestHandler){
			
			return true;
		}
		
		//排除公共资源公共资源不进行拦截。
		
		Set<String> publicSet = new HashSet<String>();
		publicSet.add("/guest/user/login");
		publicSet.add("/guest/user/toLoginUI");
		
		publicSet.add("/guest/user/regist");
		publicSet.add("/guest/user/toRegistUI");
		publicSet.add("/guest/user/logout");
		
		
		//针对后台操作添加公共资源
		publicSet.add("/manager/admin/toMainUI");
		publicSet.add("/manager/admin/toLoginUI");
		publicSet.add("/manager/admin/login");
		publicSet.add("/manager/admin/logout");
		//获取当前请求路径。
		String servletPath = request.getServletPath();
		if(publicSet.contains(servletPath)){
			return true;
		}
			
		HttpSession session = request.getSession();
		
		
		if(servletPath.startsWith("/guest")){
			User user = (User)session.getAttribute(GlobaleNames.LOGIN_USER);
			
			if(user == null){
				throw new UserOperationForbiddenException(GlobaleMessage.USER_OPERATION_FORBIDDEN);
			}
		}
		
		if(servletPath.startsWith("/manager")){
			Admin admin = (Admin)session.getAttribute(GlobaleNames.LOGIN_Admin);
			
			if(admin==null){
					throw new AdminOperationForbiddenException(GlobaleMessage.Admin_OPERATION_FORBIDDEN);
				}
		}
		
		return true;
	}
}

