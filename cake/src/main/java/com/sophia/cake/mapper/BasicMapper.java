package com.sophia.cake.mapper;

import com.sophia.cake.entity.po.Basic;
import com.sophia.cake.entity.vo.FormulaVo;

/**
 * @author lizhe
 * @date 2020-03-24 22:35
 */
public interface BasicMapper {
    // 查找所有的基础产品
    int findBasics();
    // 添加基础产品
    int addBasic(Basic basic);
    //  添加原材料
    int add(FormulaVo formulaVo);
}
