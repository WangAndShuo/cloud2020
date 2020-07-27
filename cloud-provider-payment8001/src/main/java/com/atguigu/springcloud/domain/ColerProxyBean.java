package com.atguigu.springcloud.domain;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
public class ColerProxyBean implements ColerProxy {


    @Override
    public Integer add(Integer i, Integer j) {
        Integer integer = i+j;
        return  integer;
    }

    @Override
    public Integer sub(Integer i, Integer j) {
        Integer integer = i-j;
        return  integer;
    }
}
