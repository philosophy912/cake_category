package com.sophia.cake;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sophia.cake.mapper")
public class CakeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CakeApplication.class, args);
    }

}
