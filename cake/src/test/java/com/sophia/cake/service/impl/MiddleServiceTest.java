package com.sophia.cake.service.impl;

import com.sophia.cake.entity.vo.MiddleVo;
import com.sophia.cake.service.impl.MiddleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author lizhe
 * @date 2020-03-31 21:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class MiddleServiceTest {
    @Autowired
    private MiddleService service;

    @Test
    void query() {
    }

    @Test
    void queryName() {
        List<MiddleVo> middleVos = service.queryName("±ù°ôµ°¸â12¸ö");
        log.debug("middleVos = {}", middleVos);
    }

    @Test
    void add() {
    }

    @Test
    void delete() {
        MiddleVo vo = new MiddleVo();
        vo.setId(5);
        service.delete(vo);
    }

    @Test
    void update() {
        MiddleVo middleVo = service.queryName("±ù°ôµ°¸â12¸ö").get(0);
        middleVo.setCapacity(12f);
        middleVo.setUnit("ºÐ");
        middleVo.getFormulas().forEach(formulaVo -> formulaVo.setCount(formulaVo.getCount() + 1));
        service.update(middleVo);
    }
}