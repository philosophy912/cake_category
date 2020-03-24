package com.sophia.cake.service.impl;

import com.sophia.cake.entity.po.Basic;
import com.sophia.cake.entity.po.MaterialFormula;
import com.sophia.cake.entity.vo.BasicVo;
import com.sophia.cake.entity.bo.MaterialFormulaBo;
import com.sophia.cake.mapper.BasicMapper;
import com.sophia.cake.mapper.MaterialFormulaMapper;
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
public class BasicService implements IService<Basic> {

    @Resource
    private BasicMapper basicMapper;
    @Resource
    private MaterialFormulaMapper materialFormulaMapper;


    public List<BasicVo> findBasics(){
        return basicMapper.findBasics();
    }

    @Override
    public List<Basic> findAll() {
        return basicMapper.findAll();
    }

    @Override
    @Transactional
    public boolean add(Basic basic) {
        int count = 0;
        count += basicMapper.add(basic);
        for (MaterialFormula formula : basic.getMaterialFormulaSet()) {
            count += materialFormulaMapper.add(formula);
        }
        if (count != (basic.getMaterialFormulaSet().size() + 1)) {
            throw new RuntimeException("add basic failed");
        }
        return true;
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        int count = 0;
        count += basicMapper.delete(id);
        count += materialFormulaMapper.deleteBasic(id);
        if (count != 2) {
            throw new RuntimeException("delete basic failed");
        }
        return true;
    }

    @Override
    @Transactional
    public boolean update(Basic basic) {
        // 增加Transactional注解控制事物，当所有成功才成功
        int count = 0;
        int result = basicMapper.update(basic);
        count += result;
        int basicId = basic.getId();
        Set<MaterialFormula> materialFormulas = basic.getMaterialFormulaSet();
        for (MaterialFormula formula : materialFormulas) {
            int success = materialFormulaMapper.updateFormula(convert(formula, basicId));
            count += success;
        }
        if (count != (1 + materialFormulas.size())) {
            throw new RuntimeException("update basic failed");
        }
        return true;
    }


    private MaterialFormulaBo convert(MaterialFormula formula, Integer basicId) {
        MaterialFormulaBo vo = new MaterialFormulaBo();
        vo.setId(formula.getId());
        vo.setCount(formula.getCount());
        vo.setPrice(formula.getPrice());
        vo.setMaterialId(formula.getMaterial().getId());
        vo.setBasicId(basicId);
        return vo;
    }
}
