package com.sophia.cake.mapper;

import com.philosophy.base.util.NumericUtils;
import com.sophia.cake.entity.bo.MiddleBo;
import com.sophia.cake.entity.po.Middle;
import com.sophia.cake.entity.vo.BasicVo;
import com.sophia.cake.entity.vo.MiddleVo;
import com.sophia.cake.utils.EntityUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lizhe
 * @date 2020-04-06 8:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class MiddleMapperTest {
    @Resource
    private MiddleMapper mapper;
    @Autowired
    private EntityUtils utils;

    private MiddleVo middleVo;

    @BeforeEach
    void setUp() {
        middleVo = new MiddleVo();
        middleVo.setName("test");
        middleVo.setCapacity(12f);
        middleVo.setUnit("个");
        middleVo.setPrice(120f);
    }

    @Test
    void findMiddleBos() {
        List<MiddleBo> middleBos = mapper.findMiddleBos();
        log.debug("middleBos = {}", middleBos);
        assertTrue(middleBos.size() > 2);

    }

    @Test
    void findMiddleBosByName() {
        List<MiddleBo> middleBos = mapper.findMiddleBosByName("%蛋糕%");
        log.debug("middleBos = {}", middleBos);
        assertTrue(middleBos.size() > 2);
    }

    @Test
    void addMiddleVo() {
        int result = mapper.addMiddleVo(middleVo);
        assertEquals(result, 1);
    }

    @Test
    void findMiddleBoById() {
        MiddleBo middleBo = mapper.findMiddleBoById(1);
        log.debug("middleBo = {}", middleBo);
        assertNotNull(middleBo);
    }

    @Test
    void deleteMiddleById() {
        if (middleVo.getId() != null) {
            int id = middleVo.getId();
            log.debug("it will delete id = {}", id);
            int result = mapper.deleteMiddleById(id);
            assertEquals(result, 1);
        }
    }

    @Test
    void updateMiddleVo() {
        MiddleBo middleBo = mapper.findMiddleBoById(3);
        if (middleBo.getUnit().equalsIgnoreCase("毫升")) {
            middleBo.setUnit("克");
        } else {
            middleBo.setUnit("毫升");
        }
        int result = mapper.updateMiddleVo(utils.convert(middleBo));
        assertEquals(result, 1);
    }

    @Test
    void updateMiddlePrice() {
        float price = NumericUtils.randomFloat(10, 1000);
        int result = mapper.updateMiddlePrice(3, price);
        assertEquals(1, result);
    }

    @Test
    void findMiddleCount() {
        int count = mapper.findMiddleCount();
        log.debug("count = {}", count);
        assertTrue(count > 2);
    }

    @Test
    void findPageMiddleBos() {
        List<MiddleBo> middleBos = mapper.findPageMiddleBos(0, 5);
        assertEquals(5, middleBos.size());
    }

    @Test
    void findPageMiddleBosByName() {
        List<MiddleBo> middleBos = mapper.findPageMiddleBosByName("%蛋糕%", 0, 1);
        assertEquals(1, middleBos.size());
    }

    @Test
    void findBasicByNameCount() {
        int count = mapper.findBasicByNameCount("%蛋糕%");
        assertTrue(count > 1);
    }
}