package com.itheima.test;

import com.itheima.dao.ItemsDao;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author:GG
 * @Date:2022/1/27 0027
 * @Description:ssm_parent
 * @Version:1.0
 */
public class DaoTest {
    @Test
    public void testDao(){

        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("classpath:spring-dao.xml");
        ItemsDao itemsDao = app.getBean(ItemsDao.class);
        System.out.println(itemsDao.findAll());


    }
}
