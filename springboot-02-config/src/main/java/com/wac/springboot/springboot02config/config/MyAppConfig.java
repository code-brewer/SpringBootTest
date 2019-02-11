package com.wac.springboot.springboot02config.config;

import com.wac.springboot.springboot02config.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyAppConfig {

    /**
     * 将方法的返回值添加到容器中，组件的id默认为方法名
     * id=helloService
     */
    @Bean
    public HelloService helloService(){
        System.out.println("MyAppConfig给容器中添加了组件HelloService");
        return new HelloService();
    }

}
