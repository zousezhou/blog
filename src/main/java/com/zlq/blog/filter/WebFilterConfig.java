package com.zlq.blog.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Create by lanqZhou on 2020.20.24
 */
@Configuration
public class WebFilterConfig {

    @Bean
    public FilterRegistrationBean encodingFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        ActionEncodingFilter actionEncodingFilter = new ActionEncodingFilter();
        filterRegistrationBean.setFilter(actionEncodingFilter);
        filterRegistrationBean.setName("encodingFilter");
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }
}
