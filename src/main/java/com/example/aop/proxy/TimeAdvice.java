package com.example.aop.proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeAdvice implements MethodInterceptor {

    private final static Logger log = LoggerFactory.getLogger(TimeAdvice.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = invocation.proceed();

        long end = System.currentTimeMillis();

        long resultTime = end - start;

        log.info("result time = " + resultTime);

        return proceed;
    }
}
