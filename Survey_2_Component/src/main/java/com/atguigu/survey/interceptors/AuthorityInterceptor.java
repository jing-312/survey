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
 * 最中判断用户有没有相应的权限的拦截器
 * @author Administrator
 *
 */
public class AuthorityInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	private ResService resService;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if(handler instanceof DefaultServletHttpRequestHandler){
			
			return true;
		}
		
		String servletPath = request.getServletPath();
		
		servletPath = DataprocessUtils.servletPathCut(servletPath);
		
		Res res = resService.getResByPath(servletPath);
		
		Boolean publicStatus = res.getPublicStatus();
		
		if(publicStatus){
			
			return true;
		}
		
		if(servletPath.startsWith("/guest")){
			
			HttpSession session = request.getSession();
			
			User user = (User) session.getAttribute(GlobaleNames.LOGIN_USER);
			
			if(user == null) {
				throw new UserOperationForbiddenException(GlobaleMessage.USER_OPERATION_FORBIDDEN);
			}
			
			String codeArrStr = user.getCodeArrStr();
			
			boolean checkAuthority = DataprocessUtils.checkAuthority(codeArrStr, res);
			
			if(checkAuthority){
				return true;
				
			}else {
				
				throw new HasNoAuthorityException(GlobaleMessage.HAS_NO_AUTHORITY);
			}
		}
		
		if(servletPath.startsWith("/manager")){
			
			HttpSession	session = request.getSession();
			
			Admin admin = (Admin) session.getAttribute(GlobaleNames.LOGIN_Admin);
			
			if(admin == null){
				throw new AdminOperationForbiddenException(GlobaleMessage.Admin_OPERATION_FORBIDDEN);
			}
			
			String codeArrStr = admin.getCodeArrStr();
			
			boolean checkAuthority = DataprocessUtils.checkAuthority(codeArrStr, res);
			
			if(checkAuthority){
				return true;
				
			}else {
				
				throw new HasNoAuthorityException(GlobaleMessage.HAS_NO_AUTHORITY);
			}
		}
		return true;
	}
}
