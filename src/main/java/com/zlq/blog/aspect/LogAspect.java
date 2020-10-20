package com.zlq.blog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.misc.Contended;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

//切面注释 这两个注释是必须的
@Aspect
@Component
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //切入点 就是在execution中所标记的所有方法都被切入了
    @Pointcut("execution(* com.zlq.blog.web.*.*(..))")
    public void log(){

    }

    //就是被切方法之前执行的方法所用的注解
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {//JoinPoint就是切点的数据对象 可以获取到类名方法名 参数之类的
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();

        //获取request的url
        String url = httpServletRequest.getRequestURL().toString();
        //获取ip
        String ip = httpServletRequest.getRemoteAddr();

        //通过joinpoint获取类名方法名
        String classMehtod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        //通过joinpoint获取参数
        Object[]args = joinPoint.getArgs();

        RequestLog requestLog = new RequestLog(url,ip,classMehtod,args);

        logger.info("Request: {}",requestLog);
    }

    //切面方法之后执行的方法的注解
    @After("log()")
    public void doAfter(){
        logger.info("-------after-----");
    }

    //切面方法return之后执行的方法注解 returning是指定返回的参数名
    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterReturn(Object result){
        logger.info("Result: {} ",result);
    }


    private class RequestLog{

        //Request的url
        private String url;
        //ip
        private String ip;
        //方法名
        private String classMethod;
        //参数
        private Object[] args;

        private RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
