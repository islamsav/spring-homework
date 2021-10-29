package com.example.MyBookShopApp.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
@Slf4j
public class MethodExecDurationTrackedAspect {

    private Long durationMills;


    @Before(value = "execution(public UuidServiceResponse generate*(..))")
    public void beforeDurationTrackingAdvice(JoinPoint joinPoint) {
        durationMills = new Date().getTime();
        log.info("{} duration tracking begins", joinPoint.toString());
    }

    @After(value = "execution(public UuidServiceResponse generate*(..))")
    public void afterDurationTrackingAdvice(JoinPoint joinPoint) {
        durationMills = new Date().getTime() - durationMills;
        log.info("{} generateUuid() took: {} mills", joinPoint.toString(), durationMills);
    }

    @Pointcut(value = "execution(* generate*())")
    public void allUuidServicesGenerateMethods() {

    }

    @After("allUuidServicesGenerateMethods()")
    public void execAdviceForAllUuidServiceGenerateMethods(JoinPoint joinPoint) {
        log.info("{} was invoked", joinPoint.getTarget());
    }

    @Pointcut(value = "within(com.example.MyBookShopApp.aop.TestAOPController)")
    public void allMethodsOfRandomUuidServiceControllerPointCut() {
    }

    @After("allMethodsOfRandomUuidServiceControllerPointCut()")
    public void allMethodOfRandomUuidServiceControllerAdvice(JoinPoint joinPoint) {
        log.info("another {} endpoint of Uuid REST service invoked", joinPoint.toShortString());
    }
}
