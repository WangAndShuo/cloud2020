package com.atguigu.springcloud.contorller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@RestController
public class OrderZKController {

    private static  final  String INVOKE_URL= "http://cloud-payment-service";

    @Autowired
    private RestTemplate restTemplate;



    @Value("${server.port}")
    private  String serverPort;

    @GetMapping(value = "/consumer/payment/zk")
    public String paymentzk()
    {
       return restTemplate.getForObject(INVOKE_URL+"/payment/zk",String.class);
    }




}
