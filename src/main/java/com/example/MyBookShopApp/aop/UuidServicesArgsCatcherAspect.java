package com.example.MyBookShopApp.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class UuidServicesArgsCatcherAspect {

    @Pointcut("within(com.example.MyBookShopApp.aop.CustomUuidService)")
    public void uuidServicesArgCatcherPointcut(){

    }

    @AfterReturning(value = "args(rnd) && uuidServicesArgCatcherPointcut()", returning = "response")
    public void uuidServicesCatcherAdvice(Double rnd, UuidServiceResponse response) {
        response.setUuid(response.getUuid() + "_ADVICE_PART_ADDED");
        log.info("catched incoming rnd value is: {}", rnd);
    }

    @AfterThrowing(pointcut = "args(rnd) && uuidServicesArgCatcherPointcut()", throwing = "ex")
    public void uuidServicesCatcherAdvice(Double rnd, Exception ex) {
        log.info("catched incoming rnd value is: {}", ex.getLocalizedMessage());
    }
}
