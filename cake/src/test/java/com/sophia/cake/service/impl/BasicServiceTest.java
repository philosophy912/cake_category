package com.sophia.cake.service.impl;

import com.sophia.cake.entity.vo.BasicVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lizhe
 * @date 2020-04-01 21:05
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class BasicServiceTest {
    @Autowired
    private BasicService service;

    @Test
    void queryBasic() {
    }

    @Test
    void query() {
    }

    @Test
    void queryName() {
    }

    @Test
    void add() {
    }

    @Test
    void delete() {
        BasicVo basicVo = new BasicVo();
        basicVo.setId(8);
        service.delete(basicVo);
    }

    @Test
    void update() {
    }
}