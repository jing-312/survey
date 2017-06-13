package com.atguigu.survey.log.aspects;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.springframework.beans.factory.annotation.Autowired;

import com.atguigu.survey.component.service.i.LogService;
import com.atguigu.survey.entities.guest.User;
import com.atguigu.survey.entities.manager.Admin;
import com.atguigu.survey.entities.manager.Log;

import com.atguigu.survey.utils.GlobaleNames;

public class LogRecorder {
	
	@Autowired
	private LogService logService;
	
	public Object logRecord(ProceedingJoinPoint joinPoint) throws Throwable {
		
		Object returnValue = null;
		
		Object[] args = joinPoint.getArgs();//[Ljava.lang.Object@23236;
		
		Signature signature = joinPoint.getSignature();
		
		String methodName = signature.getName();
		
		String methodType = signature.getDeclaringTypeName();
		
		List<Object> inputDataList = Arrays.asList(args);
		
		String inputData = inputDataList.toString();
		
		String exceptionType = "[未捕获到异常]";
		
		String exceptionMessage = "[未捕获到异常]";
		
		try {
			
			returnValue = joinPoint.proceed(args);
			
		} catch (Throwable e) {
			
			e.printStackTrace();
			
			//先获取当异常本身的信息
			exceptionType = e.getClass().getName();
			
			exceptionMessage = e.getMessage();
			
			//尝试获取当前异常的原因
			Throwable cause = e.getCause();
			
			//如果原因存在，则使用异常原因的信息
			while(cause != null) {
				exceptionType = cause.getClass().getName();
				exceptionMessage = cause.getMessage();
				
				//继承尝试获取原因的原因
				cause = cause.getCause();
				
				//会造成无限死循环
				//cause = e.getCause();
			}
			
			/*for(Throwable cause = e.getCause();cause != null;cause = cause.getCause()) {
				exceptionType = cause.getClass().getName();
				exceptionMessage = cause.getMessage();
			}*/
			
			throw e;
			
		} finally {
			
			String operateTime = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date());
			
			String outputData = (returnValue == null) ? "[未获取到返回值]" : returnValue.toString();
			
			//从当前线程上获取request对象
			HttpServletRequest request = RequestBinder.getRequest();
			
			//通过request对象获取HttpSession对象
			HttpSession session = request.getSession();
			
			//分别尝试获取Admin和User
			User user = (User) session.getAttribute(GlobaleNames.LOGIN_USER);
			Admin admin = (Admin) session.getAttribute(GlobaleNames.LOGIN_Admin);
			
			String userPart = (user == null) ? "user未登录" : user.getUserName();
			String adminPart = (admin == null) ? "admin未登录" : admin.getAdminName();
			
			String operator = userPart + "/" + adminPart;
			
			Log log = new Log(null, operator, operateTime, methodName, methodType, inputData, outputData, exceptionType, exceptionMessage);
			logService.saveLog(log);
			
		}
		
		return returnValue;
		
	}

}
