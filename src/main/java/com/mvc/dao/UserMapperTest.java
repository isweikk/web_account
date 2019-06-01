package com.mvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mvc.pojo.*;

public class UserMapperTest {
    private ApplicationContext applicationContext;

    @Autowired
    private UserMapper mapper;

    @org.junit.Before
    public void setUp() throws Exception {
        //加载spring配置文件
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-mybatis.xml");
        //导入需要测试的
        mapper = applicationContext.getBean(UserMapper.class);
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void insert() {
//        User message = new User();
//        message.setUname("weikk3");
//        message.setUpasswd("22222222");
//        message.setCid(1001);
//        int result = mapper.insert(message);
//        System.out.println(result);
//        assert (result == 1);
    }

    @org.junit.Test
    public void selectUser() throws Exception {
        int uid = 1000010000;
        User user = mapper.selectByPrimaryKey(uid);
        System.out.println(user.getUname());
    }
}