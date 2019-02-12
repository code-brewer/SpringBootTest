package com.wac.springboot.springboot06datamybatis.mapper;

import com.wac.springboot.springboot06datamybatis.domain.Department;
import org.apache.ibatis.annotations.*;

@Mapper
public interface DepartmentMapper {

    @Select("select * from department where id = #{id}")
    public Department selectDeptById(Integer id);

    @Delete("delete from department where id = #{id}")
    public int deleteDeptById(Integer id);


    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into department(departmentName) values (#{departmentName})")
    public int insertDept(Department department);

    @Update("update department set departmentName=#{departmentName} where id = #{id}")
    public int updateDept(Department department);

}
