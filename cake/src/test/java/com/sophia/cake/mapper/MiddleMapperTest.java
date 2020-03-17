package com.sophia.cake.mapper;

import com.sophia.cake.CakeApplication;
import com.sophia.cake.entity.Middle;
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
 * @date 2020/3/17 16:14
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CakeApplication.class)
@Slf4j
class MiddleMapperTest {

    @Resource
    private MiddleMapper middleMapper;

    @Test
    void add() {
    }

    @Test
    void findAll() {
        List<Middle> middles = middleMapper.findAll();
        for (Middle middle : middles) {
            log.info("middle is {}", middle);
        }
    }
}