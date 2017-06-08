package com.atguigu.survey.component.mappers;

import com.atguigu.survey.entities.manager.Admin;
import java.util.List;

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
}