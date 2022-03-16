package com.leduoduo.health.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @Author:GG
 * @Date:2022/3/1 0001
 * @Description:health_parent
 * @Version:1.0
 */
public class ServiceApplication {
    public static void main(String[] args) throws IOException {
        new ClassPathXmlApplicationContext("classpath:spring-dubbo.xml");
        System.in.read();
    }
}
