package com.sophia.cake.mapper;

import com.sophia.cake.CakeApplication;
import com.sophia.cake.entity.Basic;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lizhe
 * @date 2020/3/17 13:24
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CakeApplication.class)
@Slf4j
class BasicMapperTest {
    @Resource
    private BasicMapper basicMapper;

    @Test
    void findAll() {
        List<Basic> basicList = basicMapper.findAll();
        log.info("size is {}", basicList.size());
        for (Basic basic : basicList) {
            log.info("basic is {}", basic);
        }

    }

    @Test
    void findByName() {
    }

    @Test
    void findById() {
        Basic basic = basicMapper.findById(1);
        log.info("basic is {}", basic);
    }

    @Test
    void add() {
    }
}