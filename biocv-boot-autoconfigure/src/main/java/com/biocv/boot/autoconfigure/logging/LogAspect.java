package com.biocv.boot.autoconfigure.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 日志切面
 *
 * @author kai
 * @date 2020/12/25 15:27
 */
@Aspect
@Component
public class LogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

//    @Pointcut("execution(* your_package.controller..*(..))")
    @Pointcut("execution(* com.biocv..*.controller..*.*(..))")
    private void requestServer() {
    }

    @Before("requestServer()")
    private void beforeRequest(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        LOGGER.info("===============================Start========================");
        LOGGER.info("IP                 : {}", request.getRemoteAddr());
        LOGGER.info("URL                : {}", request.getRequestURL().toString());
        LOGGER.info("HTTP Method        : {}", request.getMethod());
        LOGGER.info("Class Method       : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());

    }

    @Around("requestServer()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        LOGGER.info("Request Params       : {}", getRequestParams(proceedingJoinPoint));
        LOGGER.info("Result               : {}", result);
        LOGGER.info("Time Cost            : {} ms", System.currentTimeMillis() - start);

        return result;
    }

    @AfterReturning(pointcut = "requestServer()",returning = "returnValue")
    public Object after(Object returnValue){
        LOGGER.info("return value is    " + returnValue);
        return returnValue;
    }

    @AfterThrowing(pointcut = "requestServer()",throwing = "ex")
    public Object exception(Exception ex){
        LOGGER.info("exception is    " + ex);
        return ex;
    }

    protected static Map<String, Object> getRequestParams(ProceedingJoinPoint proceedingJoinPoint) {
        Map<String, Object> requestParams = new HashMap<>();

        //参数名
        String[] paramNames = ((MethodSignature)proceedingJoinPoint.getSignature()).getParameterNames();
        //参数值
        Object[] paramValues = proceedingJoinPoint.getArgs();

        for (int i = 0; i < paramNames.length; i++) {
            Object value = paramValues[i];

            //如果是文件对象
            if (value instanceof MultipartFile) {
                MultipartFile file = (MultipartFile) value;
                value = file.getOriginalFilename();  //获取文件名
            }

            requestParams.put(paramNames[i], value);
        }

        return requestParams;
    }

    @After("requestServer()")
    public void doAfter(JoinPoint joinPoint) {
        LOGGER.info("===============================End========================");
    }


}
