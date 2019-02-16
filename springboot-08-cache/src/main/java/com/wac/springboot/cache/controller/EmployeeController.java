package com.wac.springboot.cache.controller;


import com.wac.springboot.cache.domain.Employee;
import com.wac.springboot.cache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @ResponseBody
    @GetMapping("/emp/{id}")
    public Employee getEmp(@PathVariable("id") Integer id){
        return employeeService.getEmpById(id);
    }

    @ResponseBody
    @GetMapping("/emp")
    public Employee update(Employee employee){
        return employeeService.update(employee);
    }
    @GetMapping("/deleteemp")
    public String deleteEmp(Integer id){
        employeeService.deleteEmpById(id);
        return "success";
    }
    @GetMapping("/emp/lastname/{lastName}")
    public Employee getEmpByLastName(@PathVariable("lastName") String lastName){
        return employeeService.getEmpByLastName(lastName);
    }

}
