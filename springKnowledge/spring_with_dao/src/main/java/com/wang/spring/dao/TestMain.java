package com.wang.spring.dao;

import com.wang.spring.dao.config.MybatisConfiguration;
import com.wang.spring.dao.entity.Test;
import com.wang.spring.dao.service.TestService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(MybatisConfiguration.class)
@ComponentScan(basePackages = "com.wang.spring.dao")
public class TestMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestMain.class);

        TestService service = context.getBean(TestService.class);

        Test test = service.queryById(1L);
        System.out.println(test);
    }

}
