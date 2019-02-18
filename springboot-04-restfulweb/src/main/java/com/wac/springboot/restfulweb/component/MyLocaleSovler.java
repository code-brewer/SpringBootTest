package com.wac.springboot.restfulweb.component;

import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class MyLocaleSovler implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String parameter = request.getParameter("Local");

        Locale local = Locale.getDefault();
        if(!StringUtils.isEmpty(parameter)){
            String[] strs = StringUtils.split(parameter,"_");
            local = new Locale(strs[0],strs[1]);
        }

        return local;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
