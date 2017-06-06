package com.atguigu.survey.component.service.m;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.component.mappers.ResMapper;
import com.atguigu.survey.component.service.i.ResService;
import com.atguigu.survey.entities.manager.Res;

@Service
public class ResServiceImpl implements ResService {

	@Autowired
	private ResMapper resMapper;

	public boolean checkResExists(String servletPath) {
		
		int count = resMapper.checkResExists(servletPath);
		
		return count>0;
	}

	public Integer getSystemMaxPos() {
		
		return resMapper.getSystemMaxPos();
	}

	public Integer getSystemMaxCode(Integer maxPos) {
		
		return resMapper.getSystemMaxCode(maxPos);
	}

	public void saveRes(Res res) {
		resMapper.insert(res);
	}

	
}
