package com.wac.springboot.restfulweb.config;

import com.wac.springboot.restfulweb.component.MyLocaleSovler;
import com.wac.springboot.restfulweb.interceptor.LoginInterceptor;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class MyMvcConfig{


    /*
    @Bean
    public WebMvcConfigurationSupport webMvcConfigurationSupport(){

        WebMvcConfigurationSupport webMvcConfigurationSupport = new WebMvcConfigurationSupport(){

            @Override
            protected void addViewControllers(ViewControllerRegistry registry) {
                //registry.addViewController("/").setViewName("login");
               //registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }

            @Override
            protected void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler()
                super.addResourceHandlers(registry);
            }
        };

        return webMvcConfigurationSupport;
    }
    */

    /**
     *
     * @return
     */
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
                        .excludePathPatterns("/","/user/login","/index.html");

            }

            /*
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
            }
            */
        };
    }



    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleSovler();
    }

}
