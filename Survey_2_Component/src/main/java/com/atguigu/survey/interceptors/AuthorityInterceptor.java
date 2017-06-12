package com.atguigu.survey.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import com.atguigu.survey.component.service.i.ResService;
import com.atguigu.survey.e.AdminOperationForbiddenException;
import com.atguigu.survey.e.HasNoAuthorityException;
import com.atguigu.survey.e.UserOperationForbiddenException;
import com.atguigu.survey.entities.guest.User;
import com.atguigu.survey.entities.manager.Admin;
import com.atguigu.survey.entities.manager.Res;
import com.atguigu.survey.utils.DataprocessUtils;
import com.atguigu.survey.utils.GlobaleMessage;
import com.atguigu.survey.utils.GlobaleNames;

/**
 * 权限拦截器不仅要验证权限，还要取代登录拦截器，所以需要在验证权限前进行登录状态验证
 * @author Creathin
 *
 */
public class AuthorityInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private ResService resService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//1.静态资源放行
		if(handler instanceof DefaultServletHttpRequestHandler) {
			return true;
		}
		
		//2.将公共资源放行
		//①获取目标资源的servletPath
		String servletPath = request.getServletPath();
		
		//②对servletPath进行截取
		servletPath = DataprocessUtils.servletPathCut(servletPath);
		
		//③根据servletPath查询对应的Res对象
		Res res = resService.getResByPath(servletPath);
		
		//④获取目标资源的受保护状态
		Boolean publicStatus = res.getPublicStatus();
		
		//⑤检查是否是公共资源
		if(publicStatus) {
			return true;
		}
		
		//3.检查当前请求是否是guest名称空间的
		//①检查
		if(servletPath.startsWith("/guest")) {
			
			//②获取HttpSession对象
			HttpSession session = request.getSession();
			
			//③从Session域中获取User对象
			User user = (User) session.getAttribute(GlobaleNames.LOGIN_USER);
			
			//④检查user是否为null
			if(user == null) {
				throw new UserOperationForbiddenException(GlobaleMessage.USER_OPERATION_FORBIDDEN);
			}
			
			//⑤验证权限
			String codeArrStr = user.getCodeArrStr();
			boolean hasAuthority = DataprocessUtils.checkAuthority(codeArrStr, res);
			
			if(hasAuthority) {
				return true;
			}else{
				
				throw new HasNoAuthorityException(GlobaleMessage.HAS_NO_AUTHORITY);
				
			}
			
		}
		
		//4.检查当前请求是否是manager名称空间的
		if(servletPath.startsWith("/manager")) {
			
			//①验证登录状态
			HttpSession session = request.getSession();
			
			Admin admin = (Admin) session.getAttribute(GlobaleNames.LOGIN_Admin);
			
			if(admin == null) {
				
				throw new AdminOperationForbiddenException(GlobaleMessage.Admin_OPERATION_FORBIDDEN);
				
			}
			
			//②检查是否是超级管理员
			String adminName = admin.getAdminName();
			
			if("SuperAdmin".equals(adminName)) {
				
				//③如果是超级管理员，则直接放行
				return true;
				
			}
			
			//④验证权限
			String codeArrStr = admin.getCodeArrStr();
			
			boolean hasAuthority = DataprocessUtils.checkAuthority(codeArrStr, res);
			
			if(hasAuthority) {
				return true;
			}else{
				
				throw new HasNoAuthorityException(GlobaleMessage.HAS_NO_AUTHORITY);
				
			}
			
		}
		
		return true;
	}

}
