package com.wac.springboot.restfulweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

@Configuration
public class MyViewResolverConfig {


    @Bean
    public ViewResolver myViewResoler() {

        return new myViewResovler();
    }

    public static class myViewResovler implements ViewResolver{

        @Override
        public View resolveViewName(String viewName, Locale locale) throws Exception {
            return null;
        }
    }

}
