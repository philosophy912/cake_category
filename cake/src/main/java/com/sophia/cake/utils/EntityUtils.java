package com.sophia.cake.utils;

import com.sophia.cake.entity.bo.MiddleBo;
import com.sophia.cake.entity.po.Basic;
import com.sophia.cake.entity.po.BasicFormula;
import com.sophia.cake.entity.po.Material;
import com.sophia.cake.entity.po.MaterialFormula;
import com.sophia.cake.entity.po.Middle;
import com.sophia.cake.entity.vo.FormulaVo;
import com.sophia.cake.entity.vo.MiddleVo;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author lizhe
 * @date 2020/3/11 9:33
 **/
public class EntityUtils {


    public void update(Material material) {
        Float price = material.getPrice();
        Integer capacity = material.getCapacity();
        if (price != null && capacity != null) {
            material.setPricePerUnit(price / capacity);
        }
    }

    public void update(MaterialFormula materialFormula) {
        Float count = materialFormula.getCount();
        Material material = materialFormula.getMaterial();
        materialFormula.setPrice(count * material.getPricePerUnit());
    }

    public void update(Basic basic) {
        AtomicReference<Float> price = new AtomicReference<>(0f);
        basic.getMaterialFormulaSet().forEach(materialFormula -> price.updateAndGet(v -> v + materialFormula.getPrice()));
        basic.setPrice(price.get());
    }

    public void update(BasicFormula basicFormula) {
        Float count = basicFormula.getCount();
        basicFormula.setPrice(count * basicFormula.getPrice());
    }

    public void update(Middle middle) {
        AtomicReference<Float> price = new AtomicReference<>(0f);
        middle.getMaterialFormulaSet().forEach(materialFormula -> price.updateAndGet(v -> v + materialFormula.getPrice()));
        middle.getBasicFormulaSet().forEach(basicFormula -> price.updateAndGet(v -> v + basicFormula.getPrice()));
        middle.setPrice(price.get());
    }




    public MiddleVo convert(MiddleBo bo) {
        MiddleVo vo = new MiddleVo();
        vo.setId(bo.getId());
        vo.setName(bo.getName());
        vo.setUnit(bo.getUnit());
        vo.setCapacity(bo.getCapacity());
        vo.setPrice(bo.getPrice());
        Set<FormulaVo> formulas = new HashSet<>();
        formulas.addAll(bo.getBasics());
        formulas.addAll(bo.getMaterials());
        vo.setFormulas(formulas);
        return vo;
    }
}
