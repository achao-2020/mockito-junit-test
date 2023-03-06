package com.example.mokitotestdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.example.mokitotestdemo.mapper"})
public class MokitoTestDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MokitoTestDemoApplication.class, args);
    }

}
