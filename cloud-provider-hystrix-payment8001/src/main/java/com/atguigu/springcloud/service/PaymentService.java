package com.atguigu.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {


    public String paymentInfo_OK(Integer id){

        return  "线程池：   " +  Thread.currentThread().getName()+ "  paymentInfo_OK,id:   " + id +"\t" + "O(∩_∩)O哈哈";

    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String paymentInfo_TimeOut(Integer id){
        int timeNumber = 3;
        //int number = 10/0;
        try{
            TimeUnit.SECONDS.sleep(timeNumber);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return  "线程池：   " + Thread.currentThread().getName()+ "  paymentInfo_TimeOut,id:   " + id +"\t" + "O(∩_∩)O哈哈" + "耗时 "+ timeNumber +"秒钟";
    }

    public String paymentInfo_TimeOutHandler(Integer id){
        return  "线程池：   " + Thread.currentThread().getName()+ "  paymentInfo_TimeOutHandler,id:   " + id +"\t" + "/(ㄒoㄒ)/~~";

    }
}
