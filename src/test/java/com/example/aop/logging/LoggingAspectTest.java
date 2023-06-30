package com.example.aop.logging;

import com.example.aop.myclass.MyClass1;
import com.example.aop.myclass.MyClass2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@MockitoSettings
class LoggingAspectTest {

    @Mock
    MyClass1 myClass1;

    @Mock
    MyClass2 myClass2;

    @Test
    void AOPTest() throws Throwable {
        // Mock 객체 생성
        LoggingAspect loggingAspectMock = mock(LoggingAspect.class);
        JoinPoint joinPointMock = mock(JoinPoint.class);
        ProceedingJoinPoint proceedingJoinPointMock = mock(ProceedingJoinPoint.class);
        Signature mockSignature = mock(Signature.class);

        when(joinPointMock.getTarget()).thenReturn(any());
        when(joinPointMock.getTarget().getClass().getName()).thenReturn("target class name");
        when(mockSignature.getName()).thenReturn("target class method");


        myClass1.myMethod();
        myClass2.myMethod();

        verify(loggingAspectMock, times(2)).beforeAdvice(joinPointMock);
        verify(loggingAspectMock, times(2)).afterAdvice(joinPointMock);
        verify(loggingAspectMock, times(2)).aroundAdvice(proceedingJoinPointMock);
    }

}