package com.sophia.cake.service.impl;

import com.sophia.cake.CakeApplication;
import com.sophia.cake.entity.po.Basic;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author lizhe
 * @date 2020-03-19 22:39
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CakeApplication.class)
@Slf4j
class BasicServiceTest {
    @Autowired
    private BasicService basicService;

    @Test
    void findAll() {
        List<Basic> basics = basicService.findAll();
        basics.forEach(basic -> log.info("basic = {}", basic));
    }

    @Test
    void add() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }
}