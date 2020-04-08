package com.sophia.cake.utils;

import com.sophia.cake.entity.bo.MiddleBo;
import com.sophia.cake.entity.po.Material;
import com.sophia.cake.entity.vo.FormulaVo;
import com.sophia.cake.entity.vo.MiddleVo;

import java.util.HashSet;
import java.util.Set;

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

    public Integer getTotalPages(int totalRows, int pageSize) {
        return totalRows / pageSize + (totalRows % pageSize == 0 ? 0 : 1);
    }
}
