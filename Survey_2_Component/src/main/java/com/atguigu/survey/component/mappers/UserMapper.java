package com.atguigu.survey.component.mappers;

import com.atguigu.survey.entities.guest.User;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    User selectByPrimaryKey(Integer userId);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

	int getNameCount(String userName);

	User getUserByLogin(@Param("userName")String userName, @Param("userPwd")String userPwd);
}