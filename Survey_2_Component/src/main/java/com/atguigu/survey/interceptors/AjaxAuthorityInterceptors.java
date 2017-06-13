package com.atguigu.survey.interceptors;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import com.atguigu.survey.component.mappers.ResMapper;
import com.atguigu.survey.entities.guest.User;
import com.atguigu.survey.entities.manager.Admin;
import com.atguigu.survey.entities.manager.Res;
import com.atguigu.survey.utils.DataprocessUtils;
import com.atguigu.survey.utils.GlobaleNames;
import com.google.gson.Gson;
/**
 * 
 * 此拦截器实现了拦截Ajax请求，用来解决session过期导致的Ajax请求没有相应，如果没有权限返回的是相应的页面
 * Ajax相应得到的是字符串，解决了不能接受页面的反馈结果。
 *
 * @author Administrator
 *
 */
public class AjaxAuthorityInterceptors extends HandlerInterceptorAdapter {

	@Autowired
	private ResMapper resMapper;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//如果是静态资源子放行。
		if(handler instanceof DefaultServletHttpRequestHandler){
			return true;
		}
		//获取请求参数，来判断是不是Ajax请求。
		String ajaxFlag = request.getParameter("ajaxFlag");
		//如果不是Ajax请求，则直接放行。
		if(ajaxFlag == null){
			return true;
		}
		
		if(GlobaleNames.Ajax_Request.equals(ajaxFlag)){
			
			Gson gosn = new Gson();
			Map<String,Object> jsonMap = new HashMap<String, Object>();
			String jsonResult = null;
			
			//对登陆进行验证
			HttpSession session = request.getSession();
			String servletPath = request.getServletPath();
			Res res = resMapper.getResByPath(servletPath);
			
			if(servletPath.startsWith("/guest")){
				User user = (User) session.getAttribute(GlobaleNames.LOGIN_USER);
				
				if(user == null){
					jsonMap.put(GlobaleNames.Ajax_Request,"请登录后在操作！");
					
					jsonResult = gosn.toJson(jsonMap); 
					
					PrintWriter writer = response.getWriter();
							
					writer.write(jsonResult);
					
					return false;
				}
				
				String codeArrStr = user.getCodeArrStr();
				
				boolean hasAuthority = DataprocessUtils.checkAuthority(codeArrStr, res);
				
				if(hasAuthority){
					
					return true;
				}
				
				jsonMap.put(GlobaleNames.Ajax_Request,"对不起您没有相对应的操作权限！");
				
				jsonResult = gosn.toJson(jsonMap);
					
				PrintWriter writer = response.getWriter();
				
				writer.write(jsonResult);
				
				return false;
			}
			
			if(servletPath.startsWith("/manager")){
				
				Admin admin = (Admin)session.getAttribute(GlobaleNames.LOGIN_Admin);
				if(admin == null ){
					jsonMap.put(GlobaleNames.Ajax_Request,"请登录后在操作！");
					
					jsonResult = gosn.toJson(jsonMap); 
					
					PrintWriter writer = response.getWriter();
							
					writer.write(jsonResult);
					
					return false;
				}
				String adminName = admin.getAdminName();
				
				if("SuperAdmin".equals(adminName)){
					return true;
				}
				
				String codeArrStr = admin.getCodeArrStr();
				boolean hasAuthority = DataprocessUtils.checkAuthority(codeArrStr, res);
				if(hasAuthority){
					return true;
				}
				jsonMap.put(GlobaleNames.Ajax_Request, "抱歉，您没有做这个操作的权限！");
				
				jsonResult = gosn.toJson(jsonMap);
				
				PrintWriter writer = response.getWriter();
				writer.write(jsonResult);
				
				return false;
			}
		}
		
		return true;
	}
}
