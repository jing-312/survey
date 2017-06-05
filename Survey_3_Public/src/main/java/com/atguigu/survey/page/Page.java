package com.atguigu.survey.page;

import java.util.List;

/**
 * 用来封装Page类的。来完成分页功能的实现
 * 
 * @author Administrator
 *
 */
public class Page<T>{

	public List<T> list;	//用来保存每页显示多少条的数据集合。
	
	public int pageSize = 5;	//每页显示多少条。
	
	public  int pageNo;		//当前页。
	
	public int totalPageNo; //总页数。
	
	public int totalRecordNo;//总条数。
	
	//因文分页需要处理一些计算，每次都需要计算比较麻烦，直接利用构造函数来完成即可。
	
public Page(String pageNoStr, int totalRecordNo) {
		
		//1.计算总页数
		//①给totalRecordNo赋值
		this.totalRecordNo = totalRecordNo;
		
		//②执行计算
		//如果能够整除则不加1，整除加1
		this.totalPageNo = (totalRecordNo / pageSize) + ((totalRecordNo % pageSize == 0) ? 0 : 1);
		
		//2.修正pageNo
		//①给pageNo设置默认值
		this.pageNo = 1;
		
		//②类型转换
		try {
			this.pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {/*如果类型转换失败，则保持默认值*/}
		
		//③在1~totalPageNo之间进行修正
		if(pageNo > totalPageNo) {
			pageNo = totalPageNo;
		}
		
		if(pageNo < 1) {
			pageNo = 1;
		}
		
	}


	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public int getTotalPageNo() {
		return totalPageNo;
	}

	public int getTotalRecordNo() {
		return totalRecordNo;
	}
	
	
	//完成用来获取上一页下一页之类的功能。
	public boolean isHasPrev() {
		return pageNo > 1;
	}
	
	public boolean isHasNext() {
		return pageNo < totalPageNo;
	}
	
	public int getPrev() {
		return pageNo - 1;
	}
	
	public int getNext() {
		return pageNo + 1;
	}
	
	public int getIndex() {
		return (pageNo - 1)*pageSize;
	}
}
