package com.sophia.cake.mapper;

import com.sophia.cake.entity.po.BasicFormula;
import com.sophia.cake.entity.po.MaterialFormula;
import com.sophia.cake.entity.vo.FormulaVo;

import java.util.Set;

/**
 * @author lizhe
 * @date 2020/3/25 14:50
 **/
public interface FormulaMapper {
    // 添加基础产品的原材料配方
    int addBasicMaterialFormula(FormulaVo formulaVo);
    // 添加中级产品的基础产品配方
    int addMiddleBasicFormula(FormulaVo formulaVo);
    // 批量添加基础产品的原材料配方
    int addBasicMaterialFormulas(Set<FormulaVo> formulaVos);
    // 批量添加中级产品的原材料配方
    int addMiddleMaterialFormula(FormulaVo formulaVo);
    // 添加中级产品的原材料配方
    int addMiddleMaterialFormulas(Set<FormulaVo> formulaVos);
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
