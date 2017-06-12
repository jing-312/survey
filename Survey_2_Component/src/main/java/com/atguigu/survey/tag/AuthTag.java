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

/**
 * 用来实现细粒度的权限控制的具体方法。
 * @author Administrator
 *
 */
public class AuthTag extends SimpleTagSupport  {

	private String servletPath;
	
	@Override
	public void doTag() throws JspException, IOException {
		//1，先获取ioc容器的引用：
		PageContext pageContext = (PageContext) getJspContext();
		
		ServletContext servletContext = pageContext.getServletContext();
		//自带工具方法。
		WebApplicationContext ioc = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		
		ResService resService = ioc.getBean(ResService.class);
		
		Res res = resService.getResByPath(servletPath);
		
		if(res.getPublicStatus()){
			//不显示在页面。
			getJspBody().invoke(null);
			return ;
		}
		
		HttpSession session = pageContext.getSession();
		
		if(servletPath.startsWith("/guest")){
			User user = (User) session.getAttribute(GlobaleNames.LOGIN_USER);
			
			if(user != null ){
				
				String codeArrStr = user.getCodeArrStr();
				
				boolean hasAuthority = DataprocessUtils.checkAuthority(codeArrStr, res);
				
				if(hasAuthority){
					getJspBody().invoke(null);
					return ;
				}
			}
		}
		
		if(servletPath.startsWith("/manager")){
			Admin admin = (Admin)session.getAttribute(GlobaleNames.LOGIN_Admin);
			
			
			if(admin != null ){
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
		this.servletPath = "/"+servletPath;
	}
}

