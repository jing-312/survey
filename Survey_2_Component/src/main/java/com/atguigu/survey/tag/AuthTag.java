package com.atguigu.survey.tag;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.atguigu.survey.component.service.i.ResService;
import com.atguigu.survey.entities.guest.User;
import com.atguigu.survey.entities.manager.Admin;
import com.atguigu.survey.entities.manager.Res;
import com.atguigu.survey.utils.DataprocessUtils;
import com.atguigu.survey.utils.GlobaleNames;

public class AuthTag extends SimpleTagSupport {
	
	private String servletPath;
	
	@Override
	public void doTag() throws JspException, IOException {
		
		//1.准备工作
		PageContext pageContext = (PageContext) getJspContext();
		
		ServletContext servletContext = pageContext.getServletContext();
		
		//获取IOC容器对象的引用
		WebApplicationContext ioc = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		
		ResService resService = ioc.getBean(ResService.class);
		
		//2.根据servletPath查询Res对象
		Res res = resService.getResByPath(servletPath);
		if(res != null){
			//3.检查是否为公共资源
			if(res.getPublicStatus()) {
				
				//4.执行标签体并返回函数
				getJspBody().invoke(null);
				return ;
				
			}
		}
		
		
		//5.检查是否登录
		HttpSession session = pageContext.getSession();
		
		if(servletPath.startsWith("/guest")) {
			
			User user = (User) session.getAttribute(GlobaleNames.LOGIN_USER);
			
			if(user != null) {
				
				//6.如果已经登录，则继续检查是否有权限
				String codeArrStr = user.getCodeArrStr();
				boolean hasAuthority = DataprocessUtils.checkAuthority(codeArrStr, res);
				if(hasAuthority) {
					
					//7.如果有权限，则执行标签体并返回函数
					getJspBody().invoke(null);
					return ;
					
				}
				
			}
			
		}
		
		if(servletPath.startsWith("/manager")) {
			
			Admin admin = (Admin) session.getAttribute(GlobaleNames.LOGIN_Admin);
			
			if(admin != null) {
				
				String adminName = admin.getAdminName();
				
				if("SuperAdmin".equals(adminName)) {
					getJspBody().invoke(null);
					return ;
				}
				
				String codeArrStr = admin.getCodeArrStr();
				
				boolean hasAuthority = DataprocessUtils.checkAuthority(codeArrStr, res);
				
				if(hasAuthority) {
					getJspBody().invoke(null);
					return ;
				}
				
			}
			
		}
		
	}
	
	public void setServletPath(String servletPath) {
		this.servletPath = servletPath;
	}
	
}
