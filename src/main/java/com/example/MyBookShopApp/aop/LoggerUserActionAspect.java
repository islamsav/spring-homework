package com.example.MyBookShopApp.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggerUserActionAspect {


    @Pointcut("@annotation(com.example.MyBookShopApp.annotations.UserActionToCartLoggable)")
    public void loggingUserActionToPostponedPointcut() {
    }

    @AfterReturning(pointcut = "loggingUserActionToPostponedPointcut()")
    public void logging() {
        log.info("TEST!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }
}
