package com.atguigu.survey.component.service.m;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.component.mappers.AdminMapper;
import com.atguigu.survey.component.mappers.ResMapper;
import com.atguigu.survey.component.service.i.AdminService;
import com.atguigu.survey.e.AdminLoginFailedException;
import com.atguigu.survey.e.AdminNameExistsException;
import com.atguigu.survey.entities.manager.Admin;
import com.atguigu.survey.entities.manager.Role;
import com.atguigu.survey.utils.DataprocessUtils;
import com.atguigu.survey.utils.GlobaleMessage;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminMapper adminMapper;

	@Autowired
	private ResMapper resMapper;
	
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
		
		int conut = adminMapper.getAdminCountByAdminName(adminName);
		
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

	public List<Integer> getCurrentRoleIdList(Integer adminId) {
		return adminMapper.getCurrentRoleIdList(adminId);
	}

	public void updateRelationship(Integer adminId,List<Integer> roleIdList) {
		
			adminMapper.deleteOldRelationship(adminId);
			
			if(roleIdList != null) {
				adminMapper.saveNewRelationship(adminId, roleIdList);
			}
			
			Set<Role> roleSet = adminMapper.getRoleSetDeeply(adminId);
			
			Integer maxPos = resMapper.getSystemMaxPos();
			
			String codeArr = DataprocessUtils.calculateCodeArr(roleSet, maxPos);
			
			adminMapper.updateCodeArr(adminId, codeArr);
			
		}
	
	
}
