package com.example.MyBookShopApp.aop;

import com.example.MyBookShopApp.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggerUserActionAspect {

    private final BookRepository bookRepository;

    @Autowired
    public LoggerUserActionAspect(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Pointcut("@annotation(com.example.MyBookShopApp.annotations.UserAddSlugToCartLoggable)")
    public void addToCartLoggable() {
    }

    @Pointcut("@annotation(com.example.MyBookShopApp.annotations.RemoveToCartLoggable)")
    public void removeBook() {
    }

    @Pointcut(value = "execution(* *Page())")
    public void getPage() {
    }

    @After("getPage()")
    public void getPageLoggable(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        log.info("AOP - execute method {} and return {} page", method, method);
    }

    @Before("addToCartLoggable()")
    public void afterLogging(JoinPoint joinPoint) {
        String slug = joinPoint.getArgs()[0].toString();
        String title = bookRepository.findBookEntityBySlug(slug).getTitle();
        log.info("AOP - user add book {} to cart", title);
    }

    @Before("removeBook()")
    public void beforeLogging(JoinPoint joinPoint) {
        String slug = joinPoint.getArgs()[0].toString();
        String title = bookRepository.findBookEntityBySlug(slug).getTitle();
        log.info("AOP - user remove book {} from cart", title);
    }
}
