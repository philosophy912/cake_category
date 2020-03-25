package com.sophia.cake.mapper;

import com.sophia.cake.entity.po.BasicFormula;
import com.sophia.cake.entity.po.MaterialFormula;
import com.sophia.cake.entity.vo.FormulaVo;

/**
 * @author lizhe
 * @date 2020/3/25 14:50
 **/
public interface FormulaMapper {
    // 添加原材料配方
    int addMaterialFormula(FormulaVo formulaVo);
    // 添加基础产品配方
    int addBasicFormula(FormulaVo formulaVo);
    // 更新原材料配方
    int updateMaterialFormula(FormulaVo formulaVo);
    // 更新原材料配方
    int updateBasicFormula(FormulaVo formulaVo);
    // 根据ID查找原材料配方
    FormulaVo findMaterialFormulaVoById(Integer id);
    // 根据ID查找基础产品配方
    FormulaVo findBasicFormulaVoById(Integer id);
    // 根据ID删除原材料配方
    int deleteMaterialFormula(Integer id);
    // 根据ID删除原材料配方
    int deleteBasicFormula(Integer id);
    // 根据ID查找MaterialFormula
    MaterialFormula findMaterialFormulaById(Integer id);
    // 根据ID查找BasicFormula
    BasicFormula findBasicFormulaById(Integer id);
}
