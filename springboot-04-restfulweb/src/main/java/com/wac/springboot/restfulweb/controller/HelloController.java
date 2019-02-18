package com.wac.springboot.restfulweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Map;

@Controller
public class HelloController {


    @RequestMapping({"/"})
    public String login(){
        return "login";
    }


    //http://localhost:8080/success
    @RequestMapping("/success")
    public String success(Map<String,Object> map){
        map.put("hello","<h4>hello</h4>");
        map.put("names", Arrays.asList(1,2,3,4));
        return "success";
    }
}
