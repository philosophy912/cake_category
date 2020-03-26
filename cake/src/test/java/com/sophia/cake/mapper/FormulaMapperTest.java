package com.sophia.cake.mapper;

import com.sophia.cake.entity.po.BasicFormula;
import com.sophia.cake.entity.po.MaterialFormula;
import com.sophia.cake.entity.vo.FormulaType;
import com.sophia.cake.entity.vo.FormulaVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lizhe
 * @date 2020/3/25 15:04
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class FormulaMapperTest {

    @Resource
    private FormulaMapper mapper;


    @Test
    void addMaterialFormula() {
        FormulaVo formulaVo = new FormulaVo();
        formulaVo.setCount(120f);
        formulaVo.setPrice(12.0f);
        formulaVo.setType(FormulaType.MATERIAL.getValue());
        formulaVo.setId(3);
        formulaVo.setPid(2);
        int result = mapper.addBasicMaterialFormula(formulaVo);
        log.info("formula vo = {}", formulaVo.getFid());
        log.info("result = {}", result);
    }

    @Test
    void addBasicFormula() {
        FormulaVo formulaVo = new FormulaVo();
        formulaVo.setCount(120f);
        formulaVo.setPrice(12.0f);
        formulaVo.setType(FormulaType.BASIC.getValue());
        formulaVo.setId(30);
        formulaVo.setPid(2);
        int result = mapper.addMiddleBasicFormula(formulaVo);
        log.info("formula vo = {}", formulaVo.getFid());
        log.info("result = {}", result);
    }

    @Test
    void updateMaterialFormula() {
        FormulaVo vo = mapper.findMaterialFormulaVoById(2);
        vo.setPid(3);
        int result = mapper.updateMaterialFormula(vo);
        log.info("result = {}", result);
    }

    @Test
    void updateBasicFormula() {
        FormulaVo vo = mapper.findBasicFormulaVoById(2);
        vo.setPid(3);
        int result = mapper.updateBasicFormula(vo);
        log.info("result = {}", result);
    }

    @Test
    void findMaterialFormulaVoById() {
        FormulaVo vo = mapper.findMaterialFormulaVoById(2);
        log.info("vo = {}", vo);
    }

    @Test
    void findBasicFormulaVoById() {
        FormulaVo vo = mapper.findBasicFormulaVoById(7);
        log.info("vo = {}", vo);
    }

    @Test
    void deleteMaterialFormula() {
        int result = mapper.deleteMaterialFormula(165);
        log.debug("result = {}", result);
    }

    @Test
    void deleteBasicFormula() {
        int result = mapper.deleteBasicFormula(9);
        log.debug("result = {}", result);
    }

    @Test
    void findMaterialFormulaById() {
        MaterialFormula formula = mapper.findMaterialFormulaById(12);
        log.debug("formula = {}", formula);
    }

    @Test
    void findBasicFormulaById() {
        BasicFormula formula = mapper.findBasicFormulaById(2);
        log.debug("formula = {}", formula);
    }

    @Test
    void addBasicMaterialFormulas() {
        Set<FormulaVo> vos = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            FormulaVo vo = new FormulaVo();
            vo.setCount(1f + i);
            vo.setPrice(12f + i);
            vo.setType(FormulaType.MATERIAL.getValue());
            vo.setName("123" + i);
            vo.setId(1 + i);
            vo.setPid(1 + i);
            vos.add(vo);
        }
        int result = mapper.addBasicMaterialFormulas(vos);
        log.debug("result = {}", result);

    }

    @Test
    void addMiddleMaterialFormula() {
        Set<FormulaVo> vos = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            FormulaVo vo = new FormulaVo();
            vo.setCount(1f + i);
            vo.setPrice(12f + i);
            vo.setType(FormulaType.MATERIAL.getValue());
            vo.setName("123" + i);
            vo.setId(1 + i);
            vo.setPid(1 + i);
            vos.add(vo);
        }
        int result = mapper.addMiddleMaterialFormulas(vos);
        log.debug("result = {}", result);
    }
}