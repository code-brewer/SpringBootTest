package com.wac.springboot.springboot06datamybatis.controller;

import com.wac.springboot.springboot06datamybatis.domain.Employee;
import com.wac.springboot.springboot06datamybatis.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmpController {

    @Autowired
    EmployeeMapper employeeMapper;

    @GetMapping("/get/{id}")
    public Employee getEmpById(@PathVariable("id") Integer id){
        return employeeMapper.selectEmpById(id);
    }

    @GetMapping("/emp")
    public Employee insertEmp(Employee employee){

        employeeMapper.insertEmp(employee);
        return employee;
    }

}
