package com.sophia.cake.mapper;

import com.sophia.cake.entity.FormulaType;
import com.sophia.cake.entity.vo.FormulaVo;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.mapping.Formula;
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
 * @date 2020-04-06 21:16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class BasicFormulaMapperTest {
    @Resource
    private BasicFormulaMapper mapper;

    private FormulaVo formulaVo;

    @BeforeEach
    void setUp() {
        formulaVo = new FormulaVo();
        formulaVo.setCount(120f);
        formulaVo.setPrice(120f);
        formulaVo.setType(FormulaType.BASIC.getValue());
        formulaVo.setId(15);
        formulaVo.setPid(2);
    }

    @Test
    void addFormulaVo() {
        int result = mapper.addFormulaVo(formulaVo);
        assertEquals(1, result);
        log.debug("formulaVo = {}", formulaVo);
    }

    @Test
    void deleteFormulaVoById() {
        int result = mapper.deleteFormulaVoById(120);
        assertEquals(0, result);
    }

    @Test
    void updateFormulaVo() {
        FormulaVo formulaVo = mapper.findFormulaVoById(2).get(0);
        if (formulaVo.getPrice() > 100) {
            formulaVo.setPrice(10f);
        } else {
            formulaVo.setPrice(120f);
        }
        int result = mapper.updateFormulaVo(formulaVo);
        assertEquals(1, result);
    }

    @Test
    void findFormulaVoById() {
        List<FormulaVo> formulaVos = mapper.findFormulaVoById(2);
        assertTrue(formulaVos.size() > 0);
    }
}