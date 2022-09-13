package com.rh.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.rh.blog.mapper")
//@EnableCaching
public class MyBlogApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MyBlogApplication.class, args);
    }

}
