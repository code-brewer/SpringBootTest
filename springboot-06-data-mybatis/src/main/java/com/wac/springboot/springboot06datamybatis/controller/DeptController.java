package com.wac.springboot.springboot06datamybatis.controller;

import com.wac.springboot.springboot06datamybatis.domain.Department;
import com.wac.springboot.springboot06datamybatis.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeptController {

    @Autowired
    private DepartmentMapper departmentMapper;


    @GetMapping("/select/{id}")
    public Department selectDeptById(@PathVariable("id") Integer id){

        return  departmentMapper.selectDeptById(id);

    }

    @GetMapping("/insert")
    public Department insertDept(Department department){
        departmentMapper.insertDept(department);
        return department;
    }


}
