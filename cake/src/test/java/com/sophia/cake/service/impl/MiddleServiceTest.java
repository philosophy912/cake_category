package com.sophia.cake.service.impl;

import com.philosophy.base.common.Pair;
import com.philosophy.base.entity.EnvData;
import com.sophia.cake.entity.FormulaType;
import com.sophia.cake.entity.bo.EntityBo;
import com.sophia.cake.entity.bo.NameBo;
import com.sophia.cake.entity.vo.FormulaVo;
import com.sophia.cake.entity.vo.MiddleVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lizhe
 * @date 2020/4/7 17:21
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class MiddleServiceTest {

    @Autowired
    private MiddleService service;

    private String basic = FormulaType.BASIC.getValue();
    private String material = FormulaType.MATERIAL.getValue();

    @Test
    void query() {
        EntityBo entityBo = new EntityBo();
        Pair<List<MiddleVo>, EnvData> query = service.query(entityBo);
        assertTrue(query.getFirst().size() > 3);
    }

    @Test
    void queryName() {
        NameBo nameBo = new NameBo();
        nameBo.setName("蛋糕");
        Pair<List<MiddleVo>, EnvData> query = service.queryName(nameBo);
        assertTrue(query.getFirst().size() > 2);
    }

    @Test
    void add() {
        MiddleVo middleVo = new MiddleVo();
        middleVo.setName("测试");
        middleVo.setCapacity(2f);
        middleVo.setUnit("张");


        FormulaVo formulaVo1 = new FormulaVo();
        formulaVo1.setCount(32f);
        formulaVo1.setType(basic);
        formulaVo1.setId(3);

        FormulaVo formulaVo2 = new FormulaVo();
        formulaVo2.setCount(42f);
        formulaVo2.setType(basic);
        formulaVo2.setId(4);

        FormulaVo formulaVo3 = new FormulaVo();
        formulaVo3.setCount(102f);
        formulaVo3.setType(material);
        formulaVo3.setId(1);

        FormulaVo formulaVo4 = new FormulaVo();
        formulaVo4.setCount(82f);
        formulaVo4.setType(material);
        formulaVo4.setId(2);

        Set<FormulaVo> formulaVos = new HashSet<>();
        formulaVos.add(formulaVo1);
        formulaVos.add(formulaVo2);
        formulaVos.add(formulaVo3);
        formulaVos.add(formulaVo4);

        middleVo.setFormulas(formulaVos);

        assertDoesNotThrow(() -> service.add(middleVo));
    }

    @Test
    void delete() {
        MiddleVo middleVo = new MiddleVo();
        middleVo.setId(120);
        assertThrows(RuntimeException.class, () -> service.delete(middleVo));
    }

    @Test
    void update() {
        EntityBo entityBo = new EntityBo();
        MiddleVo middleVo = service.query(entityBo).getFirst().get(3);
        log.debug("middleVo = {}", middleVo);
        middleVo.getFormulas().forEach(formulaVo -> formulaVo.setCount(formulaVo.getCount() + 3));
        assertDoesNotThrow(() -> service.update(middleVo));
    }
}