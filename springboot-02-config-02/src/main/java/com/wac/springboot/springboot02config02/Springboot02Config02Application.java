package com.wac.springboot.springboot02config02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Springboot02Config02Application {

    /**
     * springboot配置文件加载优先级：由高到低
     * /config/:项目根目录下的config文件中 注意不是模块的根目录
     * /:项目根目录下
     * classpath:config/application.properties
     * classpath:/application.properties
     *
     * server.context-path:/hello
     * 配置项目的访问位置
     * localhost:8080/hello
     *
     * 命令行运行时 指定配置文件位置
     * spring-config-location:
     * java -jar springboot-02-config-02-0.0.1-SNAPSHOT.jar --
     * spring.config.location=配置文件位置
     */
    public static void main(String[] args) {
        SpringApplication.run(Springboot02Config02Application.class, args);
    }

}

