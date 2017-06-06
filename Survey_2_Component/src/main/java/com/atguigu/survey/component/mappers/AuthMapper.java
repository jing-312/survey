package com.atguigu.survey.component.mappers;

import com.atguigu.survey.entities.manager.Auth;
import java.util.List;

public interface AuthMapper {
    int deleteByPrimaryKey(Integer authId);

    int insert(Auth record);

    Auth selectByPrimaryKey(Integer authId);

    List<Auth> selectAll();

    int updateByPrimaryKey(Auth record);
}