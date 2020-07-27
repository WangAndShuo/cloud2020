package com.atguigu.springcloud.domain;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 *
 *
 *  代码顺序
 *  start
 *  int i = 1;
 *  stop
 *
 *  return;
 *
 * 正常得执行顺序： @Before--》 @After ---》 @AfterReturning
 * 异常得执行顺序： @Before--》 @After ---》 @AfterThrowing
 *
 *
 * 通知执行顺序
 * 环绕前置通知--》 Before前置通知 --》 方法执行 --》环绕后置通知 --》 环绕返回通知 --》 After后置通知 --》 AfterRetunrning
 *
 * 异常通知执行顺序
 * 环绕前置通知--》 Before前置通知 --》 方法执行 --》环绕异常通知 --》 环绕返回通知 --》 After后置通知 --》 AfterRetunrning
 */
@Aspect
@Component
public class LogUtil {


    @Before("execution(* com.atguigu.springcloud..*(..))")
    public static void start(JoinPoint joinPoint){
        //获取方法签名
        final Signature signature = joinPoint.getSignature();
        //获取参数信息
        final Object[] args = joinPoint.getArgs();
        System.out.println("start......");
        System.out.println(signature.getName()+"方法开始执行，执行参数"+ Arrays.asList(args));
    }

    @AfterReturning(value = "execution(* com.atguigu.springcloud..*(..))",returning = "result")
    public static void stop(Object result){
        System.out.println("stop......"+result);
    }


    @AfterThrowing(value = "execution(* com.atguigu.springcloud..*(..))",throwing = "e")
    public static void logException(Exception e){
        System.out.println("Exception......"+e.getMessage());
    }



    @After(value = "execution(* com.atguigu.springcloud..*(..))")
    public static void logFinally(){
        System.out.println("finally......");
    }
}
