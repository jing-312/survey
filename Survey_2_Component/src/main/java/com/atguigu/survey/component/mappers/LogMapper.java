package com.atguigu.survey.component.mappers;

import com.atguigu.survey.entities.manager.Log;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface LogMapper {
    int deleteByPrimaryKey(Integer logId);

    int insert(Log record);

    Log selectByPrimaryKey(Integer logId);

    List<Log> selectAll();

    int updateByPrimaryKey(Log record);

	void createTable(@Param("tableName")String tableName);

	void saveLogIntoCurrentMonth(@Param("log") Log log,@Param("tableName") String tableName);

	List<String> getTableNames();


	List<Log> getPageList(@Param("index")int index, @Param("pageSize")int pageSize, @Param("tableName")List<String> tableName);

	int getTotalRecordNo(@Param("tableName")List<String> tableNames);
}