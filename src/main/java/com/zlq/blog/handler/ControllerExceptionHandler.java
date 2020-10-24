package com.zlq.blog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Create by lanqZhou on 2020.10.20
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    private  final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request,Exception exception) throws Exception {

        logger.error("Request URL:{},Exception :{}", request.getRequestURL(), exception);

        //判断excption对象中的状态码有没有被使用
        // 如果有则抛出异常交给springboot中注释了@ResponseStatus用了状态码的类处理异常，
        // 不然则在这个类里捕获异常并处理
        if (AnnotationUtils.findAnnotation(exception.getClass(), ResponseStatus.class)!=null){
            throw exception;
        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("URL",request.getRequestURL());
        mv.addObject("Exception",exception);
        mv.setViewName("error/error");
        return mv;
    }
}
