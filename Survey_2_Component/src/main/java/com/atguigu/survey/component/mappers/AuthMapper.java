package com.atguigu.survey.component.mappers;

import com.atguigu.survey.entities.manager.Auth;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface AuthMapper {
    int deleteByPrimaryKey(Integer authId);

    int insert(Auth record);

    Auth selectByPrimaryKey(Integer authId);

    List<Auth> selectAll();

    int updateByPrimaryKey(Auth record);

	void insert(String authName);

	void batchDelete(@Param("authIdList")List<Integer> authIdList);

	void removeOldRelationship(Integer authId);

	void saveNewRelationship(@Param("authId")Integer authId,@Param("resIdList")List<Integer> resIdList);

	List<Integer> getCurrentResIdList(Integer authId);

	

}