package com.sophia.cake.service.impl;

import com.sophia.cake.entity.BasicFormula;
import com.sophia.cake.entity.MaterialFormula;
import com.sophia.cake.entity.Middle;
import com.sophia.cake.entity.vo.BasicFormulaVo;
import com.sophia.cake.entity.vo.MaterialFormulaVo;
import com.sophia.cake.mapper.BasicFormulaMapper;
import com.sophia.cake.mapper.MaterialFormulaMapper;
import com.sophia.cake.mapper.MiddleMapper;
import com.sophia.cake.service.api.IService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @author lizhe
 * @date 2020-03-18 22:19
 */
@Service
public class MiddleService implements IService<Middle> {

    @Resource
    private MiddleMapper middleMapper;
    @Resource
    private MaterialFormulaMapper materialFormulaMapper;
    @Resource
    private BasicFormulaMapper basicFormulaMapper;

    @Override
    public List<Middle> findAll() {
        return middleMapper.findAll();
    }

    @Override
    @Transactional
    public boolean add(Middle middle) {
        int count = 0;
        count += middleMapper.add(middle);
        Set<MaterialFormula> materialFormulas = middle.getMaterialFormulaSet();
        Set<BasicFormula> basicFormulas = middle.getBasicFormulaSet();
        for (MaterialFormula formula : materialFormulas) {
            count += materialFormulaMapper.add(formula);
        }
        for (BasicFormula formula : basicFormulas) {
            count += basicFormulaMapper.add(formula);
        }
        int total = materialFormulas.size() + basicFormulas.size() + 1;
        if (count != total) {
            throw new RuntimeException("add middle failed");
        }
        return true;
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        int count = 0;
        count += middleMapper.delete(id);
        count += materialFormulaMapper.deleteMiddle(id);
        count += basicFormulaMapper.deleteMiddle(id);
        if (count != 3) {
            throw new RuntimeException("delete basic failed");
        }
        return true;
    }

    @Override
    @Transactional
    public boolean update(Middle middle) {
        // 增加Transactional注解控制事物，当所有成功才成功
        int count = 0;
        count += middleMapper.update(middle);
        int middleId = middle.getId();
        Set<MaterialFormula> materialFormulas = middle.getMaterialFormulaSet();
        Set<BasicFormula> basicFormulas = middle.getBasicFormulaSet();
        for (MaterialFormula formula : materialFormulas) {
            count += materialFormulaMapper.updateFormula(convert(formula, middleId));
        }
        for(BasicFormula formula: basicFormulas){
            count +=basicFormulaMapper.updateFormula(convert(formula, middleId));
        }
        int total = 1 + materialFormulas.size() + basicFormulas.size();
        if (count != total) {
            throw new RuntimeException("update middle failed");
        }
        return true;
    }


    private MaterialFormulaVo convert(MaterialFormula formula, Integer middleId) {
        MaterialFormulaVo vo = new MaterialFormulaVo();
        vo.setId(formula.getId());
        vo.setCount(formula.getCount());
        vo.setPrice(formula.getPrice());
        vo.setMaterialId(formula.getMaterial().getId());
        vo.setMiddleId(middleId);
        return vo;
    }

    private BasicFormulaVo convert(BasicFormula formula, Integer middleId) {
        BasicFormulaVo vo = new BasicFormulaVo();
        vo.setId(formula.getId());
        vo.setCount(formula.getCount());
        vo.setPrice(formula.getPrice());
        vo.setMiddleId(middleId);
        return vo;
    }
}
