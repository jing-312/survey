package com.atguigu.survey.component.handler.guest;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.survey.component.service.i.AuthService;
import com.atguigu.survey.e.RemoveAuthFailedException;
import com.atguigu.survey.entities.manager.Auth;
import com.atguigu.survey.utils.GlobaleMessage;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

@Controller
public class AuthHandler {

	@Autowired
	private AuthService authService;
	
	
	
	@RequestMapping("/manager/auth/batchDelete")
	public String batchDelete(@RequestParam(value="authIdList",required=false) List<Integer> authIdList ){
		if(authIdList!=null && authIdList.size()>0){
			try {
				authService.batchDelete(authIdList);
			} catch (Exception e) {
				Throwable cause = e.getCause();
				if(cause != null){
					if(cause instanceof MySQLIntegrityConstraintViolationException){
						throw new RemoveAuthFailedException(GlobaleMessage.REMOVE_AUTH_FAILED);
					}
				}
			
			}
		}
		
		return "redirect:/manager/auth/showList";
	}
	/**
	 * 完成权限列表的显示功能，查询全部权限病进行显示。
	 * @param map
	 * @return
	 */
	@RequestMapping("/manager/auth/showList")
	public String showList(Map<String,Object> map){
		
		List<Auth> authList = authService.getAllAuth();
		
		map.put("authList", authList);
		
		return "manager/auth_list";
	}
	
	/**
	 * 完成增加权限功能。
	 * @param auth
	 * @return
	 */
	@RequestMapping("/manager/auth/saveAuth")
	public String addAuth(Auth auth){
		
		authService.addAuth(auth);
		
		return "redirect:/manager/auth/showList";
		
	}
	
}
