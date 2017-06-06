package com.atguigu.survey.component.service.i;

import com.atguigu.survey.entities.manager.Res;

public interface ResService {

	boolean checkResExists(String servletPath);

	Integer getSystemMaxPos();

	Integer getSystemMaxCode(Integer maxPos);

	void saveRes(Res res);

}
