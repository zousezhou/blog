package com.zlq.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


//标签是返回状态码对应的页面 ，我们这里如果别的地方throw了我们这个异常类就会抛出异常信息
//并且返回这个状态码(如果没有aop捕获 则返回404页面)
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }


}

