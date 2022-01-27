package com.itheima.test;

import com.itheima.dao.ItemsDao;
import com.itheima.service.ItemsService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author:GG
 * @Date:2022/1/27 0027
 * @Description:ssm_parent
 * @Version:1.0
 */
public class ServiceTest {
    @Test
    public void testService(){

        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("classpath:spring-service.xml");
        ItemsService itemsService = app.getBean(ItemsService.class);

        System.out.println(itemsService.findAll());


    }
}
