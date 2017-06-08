package com.atguigu.survey.component.service.m;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.component.mappers.AdminMapper;
import com.atguigu.survey.component.service.i.AdminService;
import com.atguigu.survey.e.AdminLoginFailedException;
import com.atguigu.survey.e.AdminNameExistsException;
import com.atguigu.survey.entities.manager.Admin;
import com.atguigu.survey.utils.DataprocessUtils;
import com.atguigu.survey.utils.GlobaleMessage;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminMapper adminMapper;

	public Admin login(Admin admin) {
		
		String adminName = admin.getAdminName();
		
		String adminPwd = admin.getAdminPwd();
		
		String md5AdminPwd = DataprocessUtils.md5(adminPwd);
		
		Admin loginAdmin =adminMapper.selectAdminForLogin(adminName,md5AdminPwd);
		
		if(loginAdmin == null){
			
			throw new AdminLoginFailedException(GlobaleMessage.Admin_Login_Failed);
			
		}
		
		return loginAdmin;
		
	}

	public void saveAdmin(Admin admin) {
		String adminName = admin.getAdminName();
		
		int conut = adminMapper.selectAdminByName(adminName);
		
		if(conut>0){
			
			throw new AdminNameExistsException(GlobaleMessage.ADMIN_NAME_EXISTS);
			
		}
		
		String adminPwd = admin.getAdminPwd();
		
		String md5 = DataprocessUtils.md5(adminPwd);
		
		admin.setAdminPwd(md5);
		
		adminMapper.insert(admin);
	}

	public List<Admin> getAllAdmin() {
		
		return adminMapper.selectAll();
	}

	public void batchDelete(List<Integer> adminIdList) {
	
		adminMapper.batchDelete(adminIdList);
		
	}
	
}
