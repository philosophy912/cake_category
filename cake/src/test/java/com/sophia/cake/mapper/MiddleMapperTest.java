package com.sophia.cake.mapper;

import com.sophia.cake.entity.po.Middle;
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
 * @date 2020/3/25 16:39
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class MiddleMapperTest {
    @Resource
    private MiddleMapper mapper;

    @Test
    void findMiddles() {
        List<Middle> middles = mapper.findMiddles();
        middles.forEach(middle -> log.info("middle = {}", middle));
    }

    @Test
    void findMiddleById() {
        Middle middle = mapper.findMiddleById(2);
        log.info("middle = {}", middle);

    }

    @Test
    void addMiddle() {
        Middle middle = new Middle();
        middle.setName("middle");
        middle.setUnit("个");
        middle.setCapacity(1000f);
        middle.setPrice(200f);
        int result = mapper.addMiddle(middle);
        log.info("result = {}", result);
        log.info("id = {}", middle.getId());
    }

    @Test
    void updateMiddle() {
        Middle middle = new Middle();
        middle.setId(6);
        middle.setCapacity(1000f);
        middle.setUnit("克");
        int result = mapper.updateMiddle(middle);
        log.info("result = {}", result);
    }

    @Test
    void deleteMiddle() {
        int result = mapper.deleteMiddle(7);
        log.info("result = {}", result);
    }
}