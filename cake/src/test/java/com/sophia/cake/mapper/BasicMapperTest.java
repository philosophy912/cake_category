package com.sophia.cake.mapper;

import com.philosophy.base.util.NumericUtils;
import com.sophia.cake.entity.vo.BVo;
import com.sophia.cake.entity.vo.BasicVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lizhe
 * @date 2020-04-05 22:42
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class BasicMapperTest {
    @Resource
    private BasicMapper mapper;

    private BasicVo basicVo;

    @BeforeEach
    void setUp() {
        basicVo = new BasicVo();
        basicVo.setName("test");
        basicVo.setCapacity(12f);
        basicVo.setUnit("个");
        basicVo.setPrice(120f);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findBasicVoById() {
        BasicVo basicVo = mapper.findBasicVoById(2);
        log.debug("basicVo is {}", basicVo);
        assertNotNull(basicVo);
    }

    @Test
    void findBVos() {
        List<BVo> bVos = mapper.findBVos();
        log.debug("bVos is {}", bVos);
        assertTrue(bVos.size() > 10);

    }

    @Test
    void findBasicVos() {
        List<BasicVo> basicVos = mapper.findBasicVos();
        log.debug("basicVos is {}", basicVos);
        assertTrue(basicVos.size() > 10);
    }

    @Test
    void findBasicVosByName() {
        List<BasicVo> basicVos = mapper.findBasicVosByName("%糖%");
        log.debug("basicVos is {}", basicVos);
        assertTrue(basicVos.size() > 1);

    }

    @Test
    void addBasicVo() {
        int result = mapper.addBasicVo(basicVo);
        assertEquals(result, 1);

    }

    @Test
    void deleteBasicById() {
        int result = mapper.deleteBasicById(120);
        assertEquals(result, 0);
    }

    @Test
    void updateBasicVo() {
        BasicVo basicVo = mapper.findBasicVoById(12);
        if (basicVo.getUnit().equalsIgnoreCase("毫升")) {
            basicVo.setUnit("克");
        } else {
            basicVo.setUnit("毫升");
        }
        int result = mapper.updateBasicVo(basicVo);
        assertEquals(result, 1);
    }

    @Test
    void updateBasicPrice() {
        float price = NumericUtils.randomFloat(10, 1000);
        int result = mapper.updateBasicPrice(3, price);
        assertEquals(1, result);
    }

    @Test
    void findBasicCount() {
        int count = mapper.findBasicCount();
        log.debug("count = {}", count);
        assertTrue(count > 2);
    }

    @Test
    void findPageBasicVos() {
        List<BasicVo> basicVos = mapper.findPageBasicVos(1, 5);
        assertEquals(5, basicVos.size());
    }

    @Test
    void findPageBasicVosByName() {
        List<BasicVo> basicVos = mapper.findPageBasicVosByName("%糖%", 0, 1);
        assertEquals(1, basicVos.size());

    }

    @Test
    void findBasicByNameCount() {
        int count = mapper.findBasicByNameCount("%糖%");
        assertTrue(count > 1);
    }
}