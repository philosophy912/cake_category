package com.sophia.cake.mapper;

import com.sophia.cake.entity.po.Basic;
import com.sophia.cake.entity.vo.BVo;
import com.sophia.cake.entity.vo.BasicVo;
import com.sophia.cake.entity.vo.FormulaVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;


/**
 * @author lizhe
 * @date 2020/3/25 10:13
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class BasicMapperTest {
    @Resource
    private BasicMapper mapper;
    private FormulaVo formulaVo;
    private Basic basic;

    @BeforeEach
    private void before() {
        basic = new Basic();
        basic.setCapacity(1000f);
        basic.setUnit("升");
        basic.setName("初级产品1");
        basic.setPrice(150f);

        formulaVo = new FormulaVo();
        formulaVo.setCount(150f);
        formulaVo.setPrice(1000f);
        formulaVo.setType("原材料");
        formulaVo.setName("布丁粉");
        formulaVo.setId(2);

    }


    @Test
    void findBasics() {
        List<Basic> basics = mapper.findBasics();
        basics.forEach(basic -> log.info("basic = {}", basic));
    }

    @Test
    void findBasicById() {
        Basic basic = mapper.findBasicById(2);
        log.info("basic = {}", basic);
    }

    @Test
    void addBasic() {
        int result = mapper.addBasic(basic);
        log.info("result = {}", result);
        log.info("id = {}", basic.getId());
    }


    @Test
    void deleteBasic() {
        int result = mapper.deleteBasicById(41);
        log.info("result = {}", result);
    }

    @Test
    void updateBasic() {
        Basic basic = new Basic();
        basic.setId(38);
        basic.setName("test");
        int result = mapper.updateBasic(basic);
        log.info("result = {}", result);
    }

    @Test
    void findBasicVos() {
        List<BasicVo> basicVos = mapper.findBasicVos();
        basicVos.forEach(basicVo -> log.info("basicVo = {}", basicVo));
    }

    @Test
    void findBVos() {
        List<BVo> bVos = mapper.findBVos();
        bVos.forEach(bVo -> log.info("bVo ={}", bVo));
    }
}