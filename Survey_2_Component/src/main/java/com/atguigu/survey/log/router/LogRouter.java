package com.atguigu.survey.log.router;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.atguigu.survey.log.aspects.RoutingKeybinder;
/**
 * 用来支持数据源路由器的具体方法。
 * @author Administrator
 *
 */
public class LogRouter extends AbstractRoutingDataSource{

	@Override
	protected Object determineCurrentLookupKey() {
		
		String key = RoutingKeybinder.getKey();
		
		RoutingKeybinder.removeKey();

		return key;
	}

}
