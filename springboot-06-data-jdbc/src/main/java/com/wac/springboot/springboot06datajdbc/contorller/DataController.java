package com.wac.springboot.springboot06datajdbc.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Controller
public class DataController {


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    //@RequestMapping("/show")
    @ResponseBody
    @GetMapping("/show")
    public Map<String,Object> show(){
        List<Map<String,Object>> departments =  jdbcTemplate.queryForList("select * from department");
        return departments.get(0);
        //{"id":1,"departmentName":"开发部"}
    }
}
