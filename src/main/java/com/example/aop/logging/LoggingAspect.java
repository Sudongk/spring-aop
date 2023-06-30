package com.example.aop.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.example.aop.myclass.MyClass*.myMethod(..))")
    public void beforeAdvice(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        logger.info("{}.{} 메서드 실행 전 어드바이스.", className, methodName);
    }

    @After("execution(* com.example.aop.myclass.MyClass*.myMethod(..))")
    public void afterAdvice(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        logger.info("{}.{} 메서드 실행 후 어드바이스", className, methodName);
    }


    // @Before + @After
    @Around("execution(* com.example.aop.myclass.MyClass*.myMethod(..))")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        logger.info("{}.{} 메서드 실행 전 어드바이스", className, methodName);

        // 타겟 메서드 호출
        Object result = joinPoint.proceed();

        logger.info("{}.{} 메서드 실행 후 어드바이스", className, methodName);

        return result;
    }
}
