package com.atguigu.survey.component.service.i;

import java.util.List;

import com.atguigu.survey.entities.manager.Admin;

public interface AdminService {

	Admin login(Admin admin);

	void saveAdmin(Admin admin);

	List<Admin> getAllAdmin();

	void batchDelete(List<Integer> adminIdList);

}
