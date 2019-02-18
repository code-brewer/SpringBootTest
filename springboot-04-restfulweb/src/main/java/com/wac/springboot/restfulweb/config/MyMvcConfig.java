package com.wac.springboot.restfulweb.config;

import com.wac.springboot.restfulweb.component.MyLocaleSovler;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;

@Configuration
public class MyMvcConfig{


    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleSovler();
    }

}
