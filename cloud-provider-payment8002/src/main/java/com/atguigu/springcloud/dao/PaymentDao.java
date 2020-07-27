package com.atguigu.springcloud.dao;

import cn.hutool.system.UserInfo;
import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
//@Repository ——插入的时候可能有问题
public interface PaymentDao
{

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") long id);

    List<UserInfo> findParamList();


}
