package com.atguigu.survey.component.service.m;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.component.mappers.AdminMapper;
import com.atguigu.survey.component.mappers.AuthMapper;
import com.atguigu.survey.component.mappers.ResMapper;
import com.atguigu.survey.component.mappers.RoleMapper;
import com.atguigu.survey.component.mappers.UserMapper;
import com.atguigu.survey.component.service.i.AuthService;
import com.atguigu.survey.entities.guest.User;
import com.atguigu.survey.entities.manager.Admin;
import com.atguigu.survey.entities.manager.Auth;
import com.atguigu.survey.entities.manager.Role;
import com.atguigu.survey.utils.DataprocessUtils;

@Service
public class AuthServiceImpl implements AuthService{

	@Autowired
	private AuthMapper authMapper;
	@Autowired
	private ResMapper resMapper;
	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;
	
	public void addAuth(Auth auth) {
		authMapper.insert(auth);
		
	}


	public List<Auth> getAllAuth() {
		
		return authMapper.selectAll();
	}


	public void batchDelete(List<Integer> authIdList) {
		
		authMapper.batchDelete(authIdList);
		
	}


	public void updateAuthName(Auth auth) {
	authMapper.updateByPrimaryKey(auth);
		
	}
	public List<Integer> getCurrentResIdList(Integer authId) {
		return authMapper.getCurrentResIdList(authId);
	}

	public void dispatcher(Integer authId, List<Integer> resIdList) {
			authMapper.removeOldRelationship(authId);
			if(resIdList != null) {
				authMapper.saveNewRelationship(authId, resIdList);
			}
			
			//所有用户权限码重新计算
			//0.查询系统中最大的权限位的值：maxPos
			Integer maxPos = resMapper.getSystemMaxPos();
			
			//1.查询全部Admin
			List<Admin> adminList = adminMapper.selectAll();
			
			//2.遍历List<Admin>
			for (Admin admin : adminList) {
				//3.获取admin对象的adminId
				Integer adminId = admin.getAdminId();
				
				//4.根据adminId深度加载roleSet
				Set<Role> roleSet = adminMapper.getRoleSetDeeply(adminId);
				
				//5.计算权限码数组值
				String codeArr = DataprocessUtils.calculateCodeArr(roleSet, maxPos);
				
				//将计算得到的新值设置到admin对象中
				admin.setCodeArrStr(codeArr);
			}
			
			//执行批量操作进行统一更新
			adminMapper.batchUpdateCodeArr(adminList);
			
			//6.查询全部User
			List<User> userList = userMapper.selectAll();
			
			//7.遍历List<User>
			for (User user : userList) {
				//8.获取user对象的角色名称：roleName
				Boolean company = user.getCompany();
				String roleName = null;
				if(company) {
					roleName = "企业用户";
				}else{
					roleName = "个人用户";
				}
				
				//9.根据roleName深度加载 roleSet
				Role role = roleMapper.getRoleDeeplyByName(roleName);
				Set<Role> roleSet = new HashSet<Role>();
				roleSet.add(role);
				
				//10.计算权限码数组值
				String codeArr = DataprocessUtils.calculateCodeArr(roleSet, maxPos);
				
				//设置回user对象
				user.setCodeArrStr(codeArr);
			}
			
			//批量操作
			userMapper.batchUpdateCodeArr(userList);
		}

		
		public void saveEntity(Auth auth) {
			
			authMapper.insert(auth);
		}


		
		
	

}
