package com.sophia.cake.service.impl;

import com.sophia.cake.entity.vo.BasicVo;
import com.sophia.cake.entity.vo.FormulaType;
import com.sophia.cake.entity.vo.FormulaVo;
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
        BasicVo basicVo = new BasicVo();
        basicVo.setName("基础产品");
        basicVo.setUnit("个");
        basicVo.setCapacity(12f);
        Set<FormulaVo> formulaVos = new HashSet<>();
        FormulaVo formula1 = new FormulaVo();
        formula1.setId(4);
        formula1.setCount(15f);
        formula1.setType("原材料");
        FormulaVo formula2 = new FormulaVo();
        formula2.setId(2);
        formula2.setCount(12f);
        formula2.setType("原材料");
        formulaVos.add(formula1);
        formulaVos.add(formula2);
        basicVo.setFormulas(formulaVos);
        service.add(basicVo);
    }

    @Test
    void delete() {
        BasicVo basicVo = new BasicVo();
        basicVo.setId(8);
        service.delete(basicVo);
    }

    @Test
    void update() {
        List<BasicVo> basicVos = service.queryName("榴莲芝士");
        log.debug("basicVos = {}", basicVos);
        BasicVo basicVo = basicVos.get(0);
        basicVo.setName(basicVo.getName() + "test");
        basicVo.getFormulas().forEach(formulaVo -> formulaVo.setCount(formulaVo.getCount() + 1));
        service.update(basicVo);
    }
}