package com.wac.springboot.springboot02config.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    @Value("${person.last-name}")
    private String name;

    //http://localhost:8080/sayHello
    @RequestMapping("/sayHello")
    public String sayHello(){

        return "hello "+name;
    }
}
