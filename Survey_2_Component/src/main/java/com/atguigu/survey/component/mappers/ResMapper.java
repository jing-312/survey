package com.atguigu.survey.component.mappers;

import com.atguigu.survey.entities.manager.Res;
import java.util.List;

public interface ResMapper {
    int deleteByPrimaryKey(Integer resId);

    int insert(Res record);

    Res selectByPrimaryKey(Integer resId);

    List<Res> selectAll();

    int updateByPrimaryKey(Res record);

	int checkResExists(String servletPath);

	Integer getSystemMaxPos();

	Integer getSystemMaxCode(Integer maxPos);

	
}