package com.wac.springboot.springboot06datamybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.wac.springboot.springboot06datamybatis.mapper")
@SpringBootApplication
public class Springboot06DataMybatisApplication {

    public static void main(String[] args) {

        SpringApplication.run(Springboot06DataMybatisApplication.class, args);
    }

}

