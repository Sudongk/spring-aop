package com.example.aop.proxy;

public class ServiceImpl implements Service {
    @Override
    public void call() {
        System.out.println("ServiceImpl.call()");
    }
}