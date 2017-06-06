package com.atguigu.survey.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import com.atguigu.survey.component.service.i.ResService;
import com.atguigu.survey.entities.manager.Res;
import com.atguigu.survey.utils.DataprocessUtils;

/**
 * 用来收集资源的拦截器，项目启用以后此拦截器可以取消使用。
 * @author Administrator
 *
 */
public class ResLnterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private ResService resService;
	@Override
	public boolean preHandle(
			HttpServletRequest request, 
			HttpServletResponse response,
			Object handler)throws Exception {
		//放行静态资源。
		if(handler instanceof DefaultServletHttpRequestHandler){
			return true;
		}
		//获取请求资源
		String servletPath = request.getServletPath();
		
		servletPath = DataprocessUtils.servletPathCut(servletPath);
		//检查此路径是否存在。
		boolean resAlreadyExists = resService.checkResExists(servletPath);
		if(resAlreadyExists){
			return true;
		}
		//如何数据库中没有此资源，则进行保存操作。
		//计算权限码与权限位。
		Integer  systemAllowedMaxCode = 1 << 30;//系统允许的最大权限码
		
		Integer resCode = 1;
		Integer resPos = 0;
		//获取最大权限位。
		Integer maxPos = resService.getSystemMaxPos();
		//确定最大权限码
		Integer maxCode = (maxPos == null) ? null : resService.getSystemMaxCode(maxPos);
		//检查权限码是否达到系统的最大值
		if(maxPos != null && maxCode != null){
			if(maxCode.equals(systemAllowedMaxCode)) {
				resPos = maxPos + 1;
				resCode = 1;
			}else{
				resPos = maxPos;
				resCode = maxCode << 1;
			}
		}
		
		Res res = new Res(null, servletPath, false, resCode, resPos);
				
		resService.saveRes(res);
		
		return true;
	}
	
}
