package com.sophia.cake;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author lizhe
 * @date 2020-04-05 21:58
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.sophia.cake.mapper")
public class CakeApplication {
    public static void main(String[] args) {
        SpringApplication.run(CakeApplication.class, args);
    }
}
