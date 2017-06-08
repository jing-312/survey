package com.atguigu.survey.component.handler.manager;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.survey.component.service.i.RoleService;
import com.atguigu.survey.e.RemoveRoleFailedException;
import com.atguigu.survey.entities.manager.Role;
import com.atguigu.survey.utils.GlobaleMessage;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;


@Controller
public class RoleHandler {

	@Autowired
	private RoleService roleService;
	
	@ResponseBody
	@RequestMapping("/manager/role/updateRole")
	public Map<String,String> updateRole(Role role){
		
	Map<String,String> jsonMap = new HashMap<String,String>();
	
	try {
		
		roleService.updateRole(role);
		
		jsonMap.put("message","success");
		
	} catch (Exception e) {
		
		e.printStackTrace();
		
		jsonMap.put("message","failed");
	}
	
		return jsonMap;
		
	}
	/**
	 * 批量删除角色的具体实现方法。
	 * @param roleIdList
	 * @return
	 */
	@RequestMapping("/manager/role/batchDelete")
	public String batchDelete(@RequestParam(value="roleIdList",required=false) List<Integer> roleIdList){
		
		try {
			roleService.batchDelete(roleIdList);
		} catch (Exception e) {
			
			Throwable cause = e.getCause();
			if(cause != null){
				if(cause instanceof MySQLIntegrityConstraintViolationException){
					throw new  RemoveRoleFailedException(GlobaleMessage.REMOVE_Role_FAILED);
				}
			}
			
		}
		
		return "redirect:/manager/role/showList";
	}
	/**
	 * 实现了角色列表全部显示功能。
	 * @param map
	 * @return
	 */
	@RequestMapping("/manager/role/showList")
	public String showList(Map<String,Object> map){
		
		List<Role> roleList = roleService.getAllRole();
		
		map.put("roleList", roleList);
		
		return "manager/role_list";
		
	}
	/**
	 * 实现了角色添加功能。
	 * @param role
	 * @return
	 */
	@RequestMapping("/manager/role/saveRole")
	public String savaRole(Role role){
		
		roleService.savaRole(role);
		
		return "redirect:/manager/role/showList";
	}
}
