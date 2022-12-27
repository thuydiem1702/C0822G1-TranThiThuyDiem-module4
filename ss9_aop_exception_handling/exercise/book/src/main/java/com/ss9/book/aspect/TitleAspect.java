package com.ss9.book.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class TitleAspect {
    @Pointcut("execution(* com.ss9.book.controller.TitleController.show*(..))")
    public void callMethod() {
    }

    @Before("callMethod()")
    public void beforeMethod(JoinPoint joinPoint) {
        System.out.println("Bạn đang vào phương thức : " + joinPoint.getSignature().getName() + ", time : " + LocalDateTime.now());
    }
}

