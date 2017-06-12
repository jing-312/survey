package com.atguigu.survey.component.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.atguigu.survey.entities.guest.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    User selectByPrimaryKey(Integer userId);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

	int getUserCount(String userName);

	User getUserByLogin(@Param("userName") String userName, @Param("userPwd") String userPwd);

	void saveRelationShip(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

	void batchUpdateCodeArr(@Param("userList") List<User> userList);
}