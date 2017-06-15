package com.atguigu.survey.log.aspects;

public class RoutingKeybinder {
	
	public static final String DATASOURCE_MAIN = "DATASOURCE_MAIN";
	public static final String DATASOURCE_LOG = "DATASOURCE_LOG";
	
	
	private static ThreadLocal<String > local = new ThreadLocal<String>();
	
	public static void bindKey(String key){
		
		local.set(key);
	}
	
	public static void removeKey(){
		local.remove();
	}
	
	public static String getKey(){
		return local.get();
	}
}
