package com.atguigu.survey.component.handler.manager;



import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.survey.component.service.i.AdminService;

import com.atguigu.survey.entities.manager.Admin;
import com.atguigu.survey.utils.GlobaleNames;

@Controller
public class AdminHandler {

	@Autowired
	private AdminService adminService;
	
	
	/**
	 * 实现了全部管理员账号的显示。及编辑页面的具体功能
	 * @param map
	 * @return
	 */
	@RequestMapping("/manager/admin/showList")
	public String showList(Map<String, Object> map){
		
		List<Admin> adminList = adminService.getAllAdmin();
		
		map.put("adminList", adminList);
		
		return "manager/admin_list";
		
	}
	/**
	 * 实现管理账号的添加。
	 * @param admin
	 * @return
	 */
	@RequestMapping("/manager/admin/saveAdmin")
	public String saveAdmin(Admin admin){
		
		adminService.saveAdmin(admin);
		
		return "redirect:/manager/admin/showList";
	}
	
	/**
	 * 实现了管理员退出的功能。
	 * @param session
	 * @return
	 */
	@RequestMapping("/manager/admin/logout")
	public String loginOut(HttpSession session){
		
		session.removeAttribute(GlobaleNames.LOGIN_Admin);
		
		return "redirect:/manager/admin/toMainUI";
		
	}
	/**
	 * 管理员登录
	 * @param admin
	 * @param session
	 * @return
	 */
	@RequestMapping("/manager/admin/login")
	public String login(Admin admin,HttpSession session){
		
		Admin loginAdmin = adminService.login(admin);
		
		session.setAttribute(GlobaleNames.LOGIN_Admin, loginAdmin);
		
		return "redirect:/manager/admin/toMainUI";
		
	}
}
