package com.zlq.blog.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class URLSessionUtil {


    /**
     * 获取url中的id值
     * @return
     */
    public static Long getId(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();
        //获取request的url
        String url = httpServletRequest.getRequestURL().toString();
        String urlid = url.substring(url.lastIndexOf("s")+2,url.lastIndexOf("/"));
        Long id = Long.valueOf(urlid);
        return id;
    }
}
