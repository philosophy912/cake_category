package com.sophia.cake.mapper;

import com.sophia.cake.entity.FormulaType;
import com.sophia.cake.entity.vo.FormulaVo;
import lombok.extern.slf4j.Slf4j;
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
 * @date 2020-04-06 21:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class MaterialFormulaMapperTest {
    @Resource
    private MaterialFormulaMapper mapper;

    private String type = FormulaType.BASIC.getValue();

    @Test
    void addBasicFormulaVo() {
        FormulaVo vo = new FormulaVo();
        vo.setCount(120f);
        vo.setPrice(100f);
        vo.setType(type);
        vo.setId(1);
        vo.setPid(1);
        int result = mapper.addBasicFormulaVo(vo);
        log.debug("vo = {}", vo);
        assertEquals(1, result);
    }

    @Test
    void addMiddleFormulaVo() {
        FormulaVo vo = new FormulaVo();
        vo.setCount(120f);
        vo.setPrice(100f);
        vo.setType(type);
        vo.setId(1);
        vo.setPid(1);
        int result = mapper.addMiddleFormulaVo(vo);
        log.debug("vo = {}", vo);
        assertEquals(1, result);
    }

    @Test
    void deleteBasicFormulaVoById() {
        int result = mapper.deleteBasicFormulaVoById(120);
        assertEquals(0, result);
    }

    @Test
    void deleteMiddleFormulaVoById() {
        int result = mapper.deleteMiddleFormulaVoById(120);
        assertEquals(0, result);
    }

    @Test
    void updateFormulaVo() {
        FormulaVo vo = mapper.findFormulaVoByBasicId(2).get(0);
        if (vo.getPrice() > 100) {
            vo.setPrice(20f);
        } else {
            vo.setPrice(120f);
        }
        int result = mapper.updateFormulaVo(vo);
        assertEquals(1, result);
    }


    @Test
    void findFormulaVoByBasicId() {
        List<FormulaVo> formulaVos = mapper.findFormulaVoByBasicId(2);
        assertTrue(formulaVos.size() > 0);
    }

    @Test
    void findFormulaVoByMiddleId() {
        List<FormulaVo> formulaVos = mapper.findFormulaVoByMiddleId(2);
        assertTrue(formulaVos.size() > 0);
    }
}