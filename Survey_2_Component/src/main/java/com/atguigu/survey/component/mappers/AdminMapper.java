package com.atguigu.survey.component.mappers;

import com.atguigu.survey.entities.manager.Admin;
import com.atguigu.survey.entities.manager.Role;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

public interface AdminMapper {
    int deleteByPrimaryKey(Integer adminId);

    int insert(Admin record);

    Admin selectByPrimaryKey(Integer adminId);

    List<Admin> selectAll();

    int updateByPrimaryKey(Admin record);

	Admin selectAdminForLogin(@Param("adminName")String adminName,@Param("md5AdminPwd") String md5AdminPwd);

	int selectAdminByName(String adminName);

	void batchDelete(@Param("adminIdList")List<Integer> adminIdList);

	List<Integer> getCurrentRoleIdList(Integer adminId);

	void deleteOldRelationship(Integer adminId);

	void saveNewRelationship(@Param("adminId")Integer adminId,
			@Param("roleIdList") List<Integer> roleIdList);

	Set<Role> getRoleSetDeeply(Integer adminId);

	void updateCodeArr(@Param("adminId")Integer adminId, @Param("codeArr")String codeArr);

	void batchUpdateCodeArr(@Param("adminList")List<Admin> adminList);

	int getAdminCountByAdminName(String adminName);
}