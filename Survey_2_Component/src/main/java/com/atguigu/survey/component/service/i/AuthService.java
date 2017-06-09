package com.atguigu.survey.component.service.i;

import java.util.List;

import com.atguigu.survey.entities.manager.Auth;

public interface AuthService {

	void addAuth(Auth auth);

	List<Auth> getAllAuth();

	void batchDelete(List<Integer> authIdList);

	void updateAuthName(Auth auth);

	void dispatcher(Integer authId, List<Integer> resIdList);

	List<Integer> getCurrentResIdList(Integer authId);


}
