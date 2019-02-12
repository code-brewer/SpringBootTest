package com.wac.springboot.springboot06datamybatis.mapper;


import com.wac.springboot.springboot06datamybatis.domain.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

public interface EmployeeMapper {

    public Employee selectEmpById(Integer id);
    public int insertEmp(Employee employee);

}
