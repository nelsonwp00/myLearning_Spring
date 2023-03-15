package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // execution : execution of a method
    // com.example.demo.service.* : any class in service package
    // public service.*.* : all public methods in service package
    // (..) : any number of parameter
    // NOTE : protected or private methods are not intercepted
    @Pointcut("execution(* com.example.demo.service.*Service.*(..))")
    private void serviceMethod() {};

    @Around("serviceMethod()")
    public void beforeService(ProceedingJoinPoint jp) throws Throwable {
        Object[] args = jp.getArgs();
        MethodSignature signature = (MethodSignature) jp.getSignature();

        logger.info(String.valueOf(signature));
        logger.info("Args : " + Arrays.toString(args));

        Object result = jp.proceed(args);

        if (signature.getReturnType() != void.class)
            logger.info(result.toString());
    }

}
