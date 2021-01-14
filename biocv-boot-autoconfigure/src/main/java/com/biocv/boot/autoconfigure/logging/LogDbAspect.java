package com.biocv.boot.autoconfigure.logging;

import com.biocv.boot.autoconfigure.logging.dao.SystemLogDao;
import com.biocv.boot.autoconfigure.logging.model.SystemLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.biocv.boot.autoconfigure.logging.LogAspect.getRequestParams;

/**
 * 日志保存到数据库切面
 *
 * @author kai
 * @date 2020/12/25 16:32
 */
@Aspect
@Component
//@ConditionalOnBean(DataSource.class)
//@EntityScan(basePackageClasses = SystemLog.class)
//@EnableJpaRepositories(basePackages = "com.biocv.boot.autoconfigure.logging")
public class LogDbAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogDbAspect.class);

    @Pointcut("@annotation(com.biocv.boot.autoconfigure.logging.DbLog)")
    public void requestServer(){

    }

    @Autowired
    private SystemLogDao systemLogDao;

    @Around("requestServer()")
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        SystemLog systemLog = new SystemLog();
        try {
            systemLog.setId(UUID.randomUUID().toString());
            systemLog.setOriginAddress(request.getRemoteAddr());
            systemLog.setUrl(request.getRequestURL().toString());
            systemLog.setMethod( joinPoint.getSignature().getDeclaringTypeName() + joinPoint.getSignature().getName());
            systemLog.setRequestParameter(getRequestParams(joinPoint).toString());
            systemLog.setTimestamp(Timestamp.valueOf(LocalDateTime.now()).toString());
            Object result = joinPoint.proceed();
            systemLog.setReturnValue(result.toString());
            LOGGER.info("inserted log into db successful");
            return result;
        }catch (Exception ex){
            systemLog.setException(ex.getMessage());
            throw ex;
        }finally {
            systemLogDao.save(systemLog);
        }
    }
}
