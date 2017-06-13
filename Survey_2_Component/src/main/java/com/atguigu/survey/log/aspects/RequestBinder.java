package com.atguigu.survey.log.aspects;

import javax.servlet.http.HttpServletRequest;

/**
 * 负责请求对象的绑定、获取和移除
 * 为了保证ThreadLocal对象在使用时是同一个对象，设置为静态
 * 相关方法为了调用方法也设置为了静态
 * @author Creathin
 *
 */
public class RequestBinder {
	
	private static ThreadLocal<HttpServletRequest> local = new ThreadLocal<HttpServletRequest>();
	
	public static void bindRequest(HttpServletRequest request) {
		local.set(request);
	}
	
	public static void removeRequest() {
		local.remove();
	}
	
	public static HttpServletRequest getRequest() {
		return local.get();
	}
}
