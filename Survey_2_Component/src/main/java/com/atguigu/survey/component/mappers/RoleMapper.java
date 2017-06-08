package com.atguigu.survey.component.mappers;

import com.atguigu.survey.entities.manager.Role;
import java.util.List;

import org.apache.ibatis.annotations.Param;

/*public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    Role selectByPrimaryKey(Integer roleId);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

	void batchDelete(@Param("roleIdList")List<Integer> roleIdList);

}*/
public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    Role selectByPrimaryKey(Integer roleId);
    
    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

	void batchDelete(@Param("roleIdList") List<Integer> roleIdList);

	List<Integer> getCurrentAuthIdList(Integer roleId);

	void removeOldRelationship(Integer roleId);

	void saveNewRelationship(@Param("roleId") Integer roleId, @Param("authIdList") List<Integer> authIdList);
}