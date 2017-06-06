package com.atguigu.survey.component.mappers;

import com.atguigu.survey.entities.manager.Res;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ResMapper {
    int deleteByPrimaryKey(Integer resId);

    int insert(Res record);

    Res selectByPrimaryKey(Integer resId);

    List<Res> selectAll();

    int updateByPrimaryKey(Res record);

	int checkResExists(String servletPath);

	Integer getSystemMaxPos();

	Integer getSystemMaxCode(Integer maxPos);

	void batchDelete(@Param("resIdList")List<Integer> resIdList);

	void updateStatus(Integer resId);

	boolean getResStatus(Integer resId);

	
}