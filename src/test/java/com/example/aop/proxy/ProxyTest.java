package com.example.aop.proxy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;

import static org.assertj.core.api.Assertions.*;

class ProxyTest {

    @Test
    @DisplayName("프록시 생성 테스트 -  MethodInterceptor를 이용한 JDK 동적 프록시를 사용하여 프록시 객체를 생성")
    void createProxy() {
        // given
        Service target = new ServiceImpl();

        // 프록시 생성을 위해 ProxyFactory 객체 생성
        ProxyFactory proxyFactory = new ProxyFactory(target);
        // 어드바이스 세팅에 MethodInterceptor를 구현한 TimeAdvice 추가
        proxyFactory.addAdvice(new TimeAdvice());

        // getProxy 메서드를 사용하여
        Service proxy = (Service) proxyFactory.getProxy();

        // 프록시 객체의 클래스와 타겟 객체의 클래스를 출력하여 비교
        System.out.println("target class : " + target.getClass());
        System.out.println("proxy class name : " + proxy.getClass());

        // 프록시 실행
        proxy.call();

        // AOP 프록시인지 확인
        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        // 대상에 인터페이스가 있는 경우 JDK 동적 프록시인지 확인
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue();
    }
}