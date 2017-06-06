package com.atguigu.survey.component.service.i;

import java.util.List;

import com.atguigu.survey.entities.manager.Res;

public interface ResService {

	boolean checkResExists(String servletPath);

	Integer getSystemMaxPos();

	Integer getSystemMaxCode(Integer maxPos);

	void saveRes(Res res);

	List<Res> getResAll();

	void batchDelete(List<Integer> resIdList);

}
