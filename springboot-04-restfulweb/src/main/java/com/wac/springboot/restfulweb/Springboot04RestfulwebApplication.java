package com.wac.springboot.restfulweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class Springboot04RestfulwebApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot04RestfulwebApplication.class, args);
    }

}
